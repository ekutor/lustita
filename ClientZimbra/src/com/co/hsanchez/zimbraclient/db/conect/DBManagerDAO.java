package com.co.hsanchez.zimbraclient.db.conect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import zimbramail.AddRecurrenceInfo;
import zimbramail.AlarmInfo;
import zimbramail.AlarmTriggerInfo;
import zimbramail.AppointmentHitInfo;
import zimbramail.ByDayRule;
import zimbramail.CalOrganizer;
import zimbramail.CalendarAttendee;
import zimbramail.CalendarAttendeeWithGroupInfo;
import zimbramail.CancelAppointmentRequest;
import zimbramail.CreateAppointmentRequest;
import zimbramail.DateTimeStringAttr;
import zimbramail.DtTimeInfo;
import zimbramail.DurationInfo;
import zimbramail.EmailAddrInfo;
import zimbramail.GetMsgResponse;
import zimbramail.IntervalRule;
import zimbramail.InvitationInfo;
import zimbramail.InviteComponentWithGroupInfo;
import zimbramail.MimePartInfo;
import zimbramail.ModifyAppointmentRequest;
import zimbramail.Msg;
import zimbramail.NumAttr;
import zimbramail.RecurrenceInfo;
import zimbramail.SearchResponse;
import zimbramail.SimpleRepeatingRule;
import zimbramail.WkDay;

import com.co.hsanchez.zimbraclient.Frecuencia;
import com.co.hsanchez.zimbraclient.Util;
import com.co.hsanchez.zimbraclient.Util.DateType;
import com.co.hsanchez.zimbraclient.db.dto.FechasReunion;
import com.co.hsanchez.zimbraclient.db.dto.NotaZimbra;
import com.co.hsanchez.zimbraclient.db.dto.User;
import com.co.hsanchez.zimbraclient.log.LogInfo;

public class DBManagerDAO extends JDBCResourceManager {
	private boolean periodic;
	private String createdBy;
	private String location;
	private String description;
	private String name_invite;

	public DBManagerDAO() {
		super();
	}

	public static String actualMeet;

	public String getPassW(User u) {

		Connection conn = null;
		PreparedStatement st = null;

		String sql = "SELECT ps FROM zimbra_auth WHERE user_id = ?";
		String res = "";

		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);

			st.setString(1, u.getIdSugar());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				res = rs.getString("ps");
				// sql =
				// "UPDATE zimbra_auth SET ps = 'forget' WHERE user_id = ?";
				// st = conn.prepareStatement(sql);
				// st.setString(1, u.getIdSugar());
				// st.executeUpdate();
			}

			return res;
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}
		return null;
	}

	public void saveToken(User u) {
		if (u.getToken() == null) {
			return;
		}
		Connection conn = null;
		PreparedStatement st = null;

		String sql = "SELECT user_id FROM zimbra_auth WHERE user_id = ?";

		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			st = conn.prepareStatement(sql);

			st.setString(1, u.getIdSugar());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				sql = "UPDATE zimbra_auth SET token_id = ?, ps = ?  WHERE user_id = ?";
				st = conn.prepareStatement(sql);
				st.setString(1, u.getToken());
				st.setString(2, "DONE");
				st.setString(3, u.getIdSugar());
			} else {
				sql = "INSERT INTO zimbra_auth (token_id,user_id,user_name) VALUES (? , ? ,? )";
				st = conn.prepareStatement(sql);
				st.setString(1, u.getToken());
				st.setString(2, u.getIdSugar());
				st.setString(3, u.getUserName());
			}
			// System.out.println(st.toString());
			int resp = st.executeUpdate();

		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}
	}

	/**
	 * Guarda las citas de zimbra en Sugar
	 * 
	 * @param time
	 * @param SearchResponse
	 * @param user
	 * @return
	 */
	public List<NotaZimbra> saveCalendar(SearchResponse sr, User u, int time) {
		LogInfo.T("Guardando Calendario::");
		List<NotaZimbra> l = new ArrayList<NotaZimbra>();
		int random = this.getRandom();

		if (sr.getHitOrCOrM() != null) {
			if (!sr.getHitOrCOrM().isEmpty()) {

				for (Object o : sr.getHitOrCOrM()) {
					AppointmentHitInfo ap = (AppointmentHitInfo) o;
					NotaZimbra n = NotaZimbra.conversor(ap, u);
					defineCreator(n);

					n.setRandom(random);

					LogInfo.T("Random::" + random + " id Usuario: "
							+ u.getIdSugar());
					LogInfo.T("Reunion recibida::" + n.getName());
					LogInfo.T("Reunion recibida::" + n.getIdZimbra());
					for (FechasReunion fecha : n.getFechas()) {
						n.setFechaActual(fecha);
						insertMeeting(n);
						l.add(n);
					}
				}
				deleteMeetings(l, random, u.getIdSugar());
			}
		}
		return l;
	}

	private int getRandom() {
		Random r = new Random();
		return r.nextInt(10000);
	}

	/**
	 * Inserta la reunion principal
	 * 
	 * @param n
	 * @return
	 */
	private boolean insertMeeting(NotaZimbra n) {
		boolean resp = false;
		String sql = "";
		LogInfo.T("Guardando Reunion::" + n.getName());
		try {
			Connection conn = null;
			PreparedStatement st = null;
			conn = getConnection();
			conn.setAutoCommit(false);

			if (meetExists(n)) {
				LogInfo.T("ACTUALIZANDO Reunion::" + n.getName());
			//	if (n.getCreator().equals(n.getModified_user_id())) {// solo
																		// debe
																		// actualizar
																		// la
																		// descripcion
																		// el
																		// creador
																		// de la
																		// reunion
					//LogInfo.T("ES CREADOR");
					sql = "UPDATE `meetings` SET "
							+ "name = ?, "
							+ "description = ?, "
							+ "location = ?, "
							+ "duration_minutes = ?, "
							+ "duration_hours = '0', "
							+ "date_start = STR_TO_DATE( ?,'%m-%d-%Y %H:%i:%s'), "
							+ "date_end = STR_TO_DATE(?,'%m-%d-%Y %H:%i:%s'), "
							+ "status = ?, " + "type = ?, " + "sequence = ?, "
							+ "external_id = ?, " + "outlook_id = ?, "
							+ "date_modified = now(), " + "deleted = '0' "
							//+ "creator = ?, " + "assigned_user_id = ?, "
							+ "WHERE id = ? ";

					LogInfo.T("Fecha Inicial:"
							+ n.getFechaActual().getDate_start());
					LogInfo.T("Fecha Final:" + n.getFechaActual().getDate_end());
					st = conn.prepareStatement(sql);
					st.setString(1, n.getName());
					st.setString(2, Util.cleanDescZimbra(n.getDesc()));
					st.setString(3, n.getLocation());
					st.setString(4, n.getDuration_minutes());
					st.setString(5, n.getFechaActual().getDate_start());
					st.setString(6, n.getFechaActual().getDate_end());
					st.setString(7, n.getStatus());
					st.setString(8, n.getType());
					st.setInt(9, n.getRandom());
					st.setString(10, n.getIdZimbra());
					st.setString(11, n.getIdZimbraIndividual());
					//st.setString(13, n.getCreator());
//					st.setString(14, n.getAssigned_user_id());
					st.setString(12, n.getId());
//				} else {
//					LogInfo.T("NO ES CREADOR" + n.getModified_user_id() + " "
//							+ n.getCreator());
//					sql = "UPDATE `meetings` SET " + "external_id = ?, "
//							+ "modified_user_id = ?, " + "type = ?, "
//							+ "date_modified = now(), " + "deleted = '0' "
//							+ "WHERE id = ? ";
//					st = conn.prepareStatement(sql);
//					st.setString(1, n.getIdZimbra());
//					st.setString(2, n.getModified_user_id());
//					st.setString(3, n.getType());
//					st.setString(4, n.getId());
//
//				}
				int act = st.executeUpdate();
				LogInfo.T("UPDATE actualizado 1::" + act);

				String sql2 = "UPDATE `meetings_users` SET"
						+ " `accept_status` = ?, deleted = '0' "
						+ "WHERE user_id = ? " + "AND meeting_id = ?";
				st = conn.prepareStatement(sql2);
				// Actualiza la relacion con el usuario sincronizador
				LogInfo.T("UPDATE accept_status::" + n.getStatus_met_det()
						+ "  asigned " + n.getCreator() + " " + n.getId());
				st.setString(1, n.getStatus_met_det());
				// st.setString(2,n.getModified_user_id());
				st.setString(2, n.getCreator());
				st.setString(3, n.getId());

				act = st.executeUpdate();
				LogInfo.T("UPDATE actualizado ::" + act);
				if (act == 0) {
					insertMettingUser(n, st, conn);
				}

			} else {
				LogInfo.T("INSERTANDO Reunion::" + n.getName());
				sql = "INSERT INTO `meetings`(`id`, `external_id`,`name`, `description`, `deleted`, `location`, "
						+ " `creator`, `duration_minutes`, `date_start`, date_end ,`status`, "
						+ "`type` ,created_by, assigned_user_id, modified_user_id, date_entered, sequence ,outlook_id," +
						"repeat_type, repeat_interval, repeat_until, repeat_count, repeat_parent_id ) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,  STR_TO_DATE( ?,'%m-%d-%Y %H:%i:%s'), STR_TO_DATE(?,'%m-%d-%Y %H:%i:%s'),"
						+ " ? , ? , ? , ?, ?, now() ,?,?, ? ,? , ? , ?, ?) ";
				
				st = conn.prepareStatement(sql);
				UUID uuidRel  = UUID.randomUUID();
				n.setId(""+uuidRel);
				st.setString(1, n.getId());
				st.setString(2, n.getIdZimbra());
				st.setString(3, n.getName());
				st.setString(4, n.getDesc());
				st.setString(5, n.getDeleted());
				st.setString(6, n.getLocation());
				st.setString(7, n.getCreator());
				st.setString(8, n.getDuration_minutes());
				st.setString(9, n.getFechaActual().getDate_start());
				st.setString(10, n.getFechaActual().getDate_end());
				st.setString(11, n.getStatus());
				st.setString(12, n.getType());
				st.setString(13, n.getCreator());
				st.setString(14, n.getAssigned_user_id());
				st.setString(15, n.getModified_user_id());
				st.setInt(16, n.getRandom());
				st.setString(17, n.getIdZimbraIndividual());
				//Repeats hack
				st.setString(18, n.getRepeatType());
				st.setString(19, n.getRepeatInterval());
				st.setString(20, n.getRepeatUntil());
				st.setString(21, n.getRepeatCount());
				st.setString(22, n.getRepeatParentId());
				st.executeUpdate();

				insertMettingUser(n, st, conn);
			}

			conn.commit();

		} catch (Exception e) {
			closeResources();
			LogInfo.E("Excepcion:: Guardando Reunion : " + n.getName());
			LogInfo.E(Util.errorToString(e));
		}
		return resp;
	}

	/**
	 * metodo para ajustar los creadores de las citas porque zimbra puede
	 * devolver un correo que no es el correcto en sugar
	 * 
	 * @param n
	 */
	private void defineCreator(NotaZimbra n) {
		try {
			String idUser = getMailId(n.getCreator());
			if (idUser == null) {
				String username = n.getCreator().split("@")[0];
				n.setCreator(getUserId(username));
			} else {
				n.setCreator(idUser);
			}
		} catch (Exception e) {

		}
		if (n.getCreator() == null) {
			n.setCreator(n.getModified_user_id());
		}

	}

	private void insertMettingUser(NotaZimbra n, PreparedStatement st,
			Connection conn) throws SQLException {
		LogInfo.T("insertandoMeeting User::" + n.getName());

		//
		String sql = "INSERT INTO `meetings_users`( `id`, `meeting_id`, `user_id`, `required`, `accept_status`,  `date_modified`, `deleted` ) "
				+ "VALUES ( ?,?,?, '1', ?,now() , 0 ) ";
		UUID uuidRel = UUID.randomUUID();
		LogInfo.T("insertandoMeeting ::" + uuidRel + " " + n.getId() + " "
				+ n.getCreator() + " " + n.getStatus_met_det());
		st = conn.prepareStatement(sql);
		st.setString(1, "" + uuidRel);
		st.setString(2, n.getId());
		st.setString(3, n.getCreator());
		st.setString(4, n.getStatus_met_det());

		st.executeUpdate();
		//
	}

	private boolean meetExists(NotaZimbra n) {
		if (n.isPeriodica()) {
			return meetPeriodicExists(n);
		}
		String sql = "SELECT id FROM `meetings` WHERE `external_id` = ? ";

		LogInfo.T("CONSULTANDO Reunion::" + n.getName() + " external_id "
				+ n.getIdZimbra() + " id indiv =" + n.getIdZimbraIndividual());
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			st = conn.prepareStatement(sql);
			st.setString(1, n.getIdZimbra());

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				LogInfo.T("Reunion ya existe por idZimbra::" + n.getName());
				n.setId(rs.getString("id"));
				return true;
			}
			sql = "SELECT id FROM `meetings` WHERE `outlook_id` = ? and created_by = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, n.getIdZimbraIndividual());
			st.setString(2, n.getCreator());
			rs = st.executeQuery();
			if (rs.next()) {
				LogInfo.T("Reunion ya existe por id Zimbra Individual ::"
						+ n.getName());
				n.setId(rs.getString("id"));
				return true;
			}

			sql = "SELECT id FROM `meetings` WHERE  `name` = ? and created_by = ? and (external_id ='' or external_id is null) ";

			st = conn.prepareStatement(sql);
			st.setString(1, n.getName());
			st.setString(2, n.getCreator());
			rs = st.executeQuery();
			if (rs.next()) {
				LogInfo.T("Reunion ya existe por Nombre ::" + n.getName());
				n.setId(rs.getString("id"));
				return true;
			}

		} catch (Exception e) {
			closeResources();
			LogInfo.E("Excepcion:: Consultando Reunion : " + n.getName());
			LogInfo.E(Util.errorToString(e));
		}
		return false;
	}

	private boolean meetPeriodicExists(NotaZimbra n) {
		String sql = "SELECT * FROM `meetings` WHERE `external_id` = ? ";
		
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			//HACK
			//obtener la periocidad de la cita para que Sugar entienda que  es periodica y ya estaba cuando modifican la fecha ya que esta informacion no la devuelve sugar

			
			st = conn.prepareStatement(sql);
			st.setString(1, n.getIdZimbra());
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				n.setRepeatType(rs.getString("repeat_type"));
				n.setRepeatInterval(rs.getString("repeat_interval"));
				n.setRepeatCount(rs.getString("repeat_count"));
				n.setRepeatParentId(rs.getString("repeat_parent_id"));
				n.setRepeatUntil(rs.getString("repeat_until"));
			}
			
			sql = "SELECT id FROM `meetings` WHERE `external_id` = ? and date_start = STR_TO_DATE( ?,'%m-%d-%Y %H:%i:%s') ";

			LogInfo.T("CONSULTANDO Reunion PERIODICA ::" + n.getName()
					+ " external_id " + n.getIdZimbra() + " id indiv ="
					+ n.getIdZimbraIndividual());
			
			st = conn.prepareStatement(sql);
			st.setString(1, n.getIdZimbra());
			st.setString(2, n.getFechaActual().getDate_start());

			rs = st.executeQuery();
			if (rs.next()) {
				LogInfo.T("Reunion PERIODICA ya existe por idZimbra::"
						+ n.getName());
				n.setId(rs.getString("id"));
				return true;
			}
			sql = "SELECT id FROM `meetings` WHERE `outlook_id` = ? and created_by = ? and date_start = STR_TO_DATE( ?,'%m-%d-%Y %H:%i:%s') ";

			st = conn.prepareStatement(sql);
			st.setString(1, n.getIdZimbraIndividual());
			st.setString(2, n.getCreator());
			st.setString(3, n.getFechaActual().getDate_start());
			rs = st.executeQuery();
			if (rs.next()) {
				LogInfo.T("Reunion ya existe por id Zimbra Individual ::"
						+ n.getName());
				n.setId(rs.getString("id"));
				return true;
			}

			sql = "SELECT id FROM `meetings` WHERE  `name` = ? and created_by = ? and (external_id ='' or external_id is null) and date_start = STR_TO_DATE( ?,'%m-%d-%Y %H:%i:%s') ";

			st = conn.prepareStatement(sql);
			st.setString(1, n.getName());
			st.setString(2, n.getCreator());
			st.setString(3, n.getFechaActual().getDate_start());
			rs = st.executeQuery();
			if (rs.next()) {
				LogInfo.T("Reunion ya existe por Nombre ::" + n.getName());
				n.setId(rs.getString("id"));
				return true;
			}

		} catch (Exception e) {
			closeResources();
			LogInfo.E("Excepcion:: Consultando Reunion : " + n.getName());
			LogInfo.E(Util.errorToString(e));
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean deleteMeetings(User u, int time) {
		LogInfo.T("Eliminar Reuniones::");
		boolean resp = false;
		String sql = "DELETE FROM meetings_users where meeting_id in (select id from meetings "
				+ "WHERE date_start >= STR_TO_DATE( ?,'%m-%d-%Y %H:%i:%s') "
				+ "AND date_end <= STR_TO_DATE( ?,'%m-%d-%Y %H:%i:%s') "
				+ "AND created_by= ? " + "AND type ='Zimbra')";

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			st.setString(1, Util.getLargeDate(DateType.START, time));
			st.setString(2, Util.getLargeDate(DateType.END, time));
			st.setString(3, u.getIdSugar());
			// System.out.println("datos  "+Util.getLargeDate(DateType.START) +
			// " "+ Util.getLargeDate(DateType.END)+ " "+ u.getIdSugar() );
			st.executeUpdate();

			sql = "DELETE FROM meetings_users "
					+ "WHERE date_modified  >= STR_TO_DATE( ?,'%m-%d-%Y %H:%i:%s') "
					+ "AND date_modified <= STR_TO_DATE( ? ,'%m-%d-%Y %H:%i:%s') "
					+ "AND user_id= ?";
			// System.out.println("sql "+sql);
			st = conn.prepareStatement(sql);
			st.setString(1, Util.getLargeDate(DateType.START, time));
			st.setString(2, Util.getLargeDate(DateType.END, time));
			st.setString(3, u.getIdSugar());

			st.executeUpdate();

			sql = "DELETE FROM meetings "
					+ "WHERE date_start >= STR_TO_DATE(?,'%m-%d-%Y %H:%i:%s') "
					+ "AND date_end <= STR_TO_DATE(?,'%m-%d-%Y %H:%i:%s') "
					+ "AND created_by=? AND type ='Zimbra'";

			st = conn.prepareStatement(sql);
			st.setString(1, Util.getLargeDate(DateType.START, time));
			st.setString(2, Util.getLargeDate(DateType.END, time));
			st.setString(3, u.getIdSugar());
			// System.out.println(sql);
			int r = st.executeUpdate();

			conn.commit();
			resp = (r == 0) ? false : true;
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}
		return resp;
	}

	public boolean deleteMeetings(List<NotaZimbra> notes, int random,
			String actualUser) {
		LogInfo.T("Eliminando  Reuniones::");
		boolean resp = false;

		for (int i = 0; i < notes.size(); i++) {

		}

		Connection conn = null;
		Statement stm = null;
		try {
			conn = getConnection();

			stm = conn.createStatement();
			String rel = getRelMeetUser(actualUser, random);
			conn.setAutoCommit(false);
			String sql = "UPDATE meetings_users SET deleted = '1' WHERE "
					+ "id IN ( # )";
			int r = 0;
			if (rel != null && rel.length() > 1) {
				sql = sql.replaceFirst("#", rel);

				LogInfo.T("Reuniones Eliminadas SQL1 :: " + sql);
				r = stm.executeUpdate(sql);
			}
			sql = "UPDATE meetings SET deleted = '1' WHERE created_by = '#' "
					+ "AND (date_start BETWEEN  STR_TO_DATE( '#','%Y/%m/%d') AND STR_TO_DATE( '#','%Y/%m/%d')) "
					+ "AND sequence <> # AND type = 'Zimbra'";
			sql = sql.replaceFirst("#", actualUser);
			sql = sql.replaceFirst("#", Util.fechaInicial);
			sql = sql.replaceFirst("#", Util.fechaFinal);
			sql = sql.replaceFirst("#", String.valueOf(random));

			LogInfo.T("Reuniones Eliminadas SQL :: " + sql);

			r += stm.executeUpdate(sql);
			conn.commit();

			resp = (r == 0) ? false : true;

			LogInfo.T("Reuniones Eliminadas:: " + r);
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}
		return resp;
	}

	public String getMailId(String mail) {
		if (mail == null) {
			return "";
		}
		String sql = "SELECT u.id FROM `email_addresses` eadd, `email_addr_bean_rel` eaddbean,`users` u "
				+ "WHERE eaddbean.email_address_id = eadd.id "
				+ "AND eaddbean.`bean_module` = 'Users' "
				+ "AND eaddbean.bean_id = u.id "
				+ "AND eadd.email_address = ? AND u.deleted = '0'";

		Connection conn = null;
		String id = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			PreparedStatement stMail = conn.prepareStatement(sql);
			stMail.setString(1, mail);
			ResultSet rs = stMail.executeQuery();

			if (rs.next()) {
				id = rs.getString("id");
			}

			LogInfo.T("ID Obtenido  mail " + mail + " sugar " + id);
			return id;
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

		return null;

	}

	public String getUserId(String userName) {
		if (userName == null) {
			return "";
		}
		String sql = "SELECT u.id FROM `users` u "
				+ "WHERE u.user_name= ? AND u.deleted = '0'";

		Connection conn = null;
		String id = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			PreparedStatement stMail = conn.prepareStatement(sql);
			stMail.setString(1, userName);
			ResultSet rs = stMail.executeQuery();
			if (rs.next()) {
				id = rs.getString("id");
			}

			return id;
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

		return null;

	}

	public String getRelMeetUser(String actualUser, int random) {

		String sql = "SELECT mu.id FROM meetings_users mu "
				+ "LEFT JOIN meetings m ON m.id = mu.meeting_id "
				+ "WHERE mu.user_id = '#' "
				+ "AND (m.date_start BETWEEN  STR_TO_DATE( '#','%Y/%m/%d') AND STR_TO_DATE( '#','%Y/%m/%d')) "
				+ "AND m.sequence <> # AND m.type = 'Zimbra'";

		sql = sql.replaceFirst("#", actualUser);
		sql = sql.replaceFirst("#", Util.fechaInicial);
		sql = sql.replaceFirst("#", Util.fechaFinal);
		sql = sql.replaceFirst("#", String.valueOf(random));

		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);

			StringBuilder sb = new StringBuilder();
			while (rs.next()) {
				sb.append("'");
				sb.append(rs.getString("id"));
				sb.append("'");
				sb.append(",");
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
			return sb.toString();
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

		return null;

	}

	/**
	 * Guarda la relacion de Reunion - Usuario
	 * 
	 * @param r
	 * @param u
	 * @param idMeet
	 * @return
	 */
	public boolean saveMeet(GetMsgResponse r, User u, String idMeet) {
		boolean resp = false;

		String sqlupd = "UPDATE `meetings_users` SET"
				+ " `accept_status` = ?, " + " `deleted` = 0,"
				+ " `required` = ? " + "WHERE meeting_id = ? AND user_id = ?";
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(true);

			if (r.getM().getInv().getComp() != null
					&& r.getM().getInv().getComp().size() > 0) {
				for (InviteComponentWithGroupInfo inv : r.getM().getInv()
						.getComp()) {

					if (inv.getFr() != null && inv.getFr().contains("...")) {
						updateMeetInfo(idMeet, inv);
					}

					boolean encontrado = false;
					String ultimo_estado = "";
					for (CalendarAttendeeWithGroupInfo cai : inv.getAt()) {

						String idUserZimbra = this.getMailId(cai.getA()); // email
																			// de
																			// cada
																			// invitado
						if (idUserZimbra == null) {
							String username = cai.getA().split("@")[0];
							idUserZimbra = getUserId(username);
						}
						if (idUserZimbra != null) {
							if (idUserZimbra.equals(u.getIdSugar())) {
								encontrado = true;
								LogInfo.T(" Usuario Actual ENCONTRADO  se actualizara el detalle");

							}
							String req = "0";
							try {
								if (cai.getRole().equals("REQ")) {
									req = "1";
								}
							} catch (java.lang.NullPointerException ne) {

							}

							// LogInfo.T("ACTUALIZANDO ESTADO  DETALLE Reunion meetings_users::"
							// + idMeet );
							ultimo_estado = NotaZimbra
									.getStatusZimbraMeetDet(cai.getPtst());
							this.updateMeetingUser(sqlupd, conn, ultimo_estado,
									req, idMeet, idUserZimbra);

						} else {
							LogInfo.T("Usuario No existe en Sugar ::"
									+ inv.getAt());
						}
					}
					if (!encontrado) {
						this.updateMeetingUser(sqlupd, conn, ultimo_estado,
								"1", idMeet, u.getIdSugar());
					}

					// conn.commit();
				}
			}

		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

		return resp;

	}

	private void updateMeetingUser(String sqlupd, Connection conn,
			String estadoReunionZimbra, String req, String idMeet,
			String idUserSugar) throws SQLException {
		PreparedStatement st = conn.prepareStatement(sqlupd);

		LogInfo.T("ACTUALIZANDO ESTADO  DETALLE Reunion consulta::"
				+ estadoReunionZimbra + " " + req + " " + idUserSugar + " "
				+ idMeet);

		st.setString(1, estadoReunionZimbra);
		st.setString(2, req);
		st.setString(3, idMeet);
		st.setString(4, idUserSugar);

		int cant = st.executeUpdate();
		LogInfo.T("CANT ::" + cant);

		if (cant == 0) {
			LogInfo.T("NO actualizo ESTADO  DETALLE Reunion meetings_users::"
					+ idMeet);
			NotaZimbra n = new NotaZimbra();
			n.setId(idMeet);
			n.setCreator(idUserSugar);
			n.setStatus_met_det(estadoReunionZimbra);
			insertMettingUser(n, st, conn);
		}

	}

	public String getMail(String idUser, String module) {

		String sql = "SELECT em.email_address FROM  email_addr_bean_rel emrel,email_addresses em "
				+ "WHERE  emrel.bean_id = ? "
				+ "AND emrel.bean_module = ? "
				+ "AND em.id = emrel.email_address_id";

		Connection conn = null;

		String mail = "";
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			PreparedStatement stMail = conn.prepareStatement(sql);
			stMail.setString(1, idUser);
			stMail.setString(2, module);
			ResultSet rs = stMail.executeQuery();

			if (rs.next()) {
				mail = rs.getString("email_address");
			}

			LogInfo.T("Mail Obtenido  sugar " + mail);
			return mail;
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

		return null;

	}

	public ResultSet gettMeetModifiedFromDB(String idMeet, User user){
	
		LogInfo.T("Obteniendo Reunion zimbra_invitee:: ");
		String sql = "SELECT zi.*,m.outlook_id, m.name as name_invite, m.created_by," +
				" m.repeat_type as repeat_type_meet, m.repeat_until as repeat_until_meet, " +
				" m.repeat_count as repeat_count_meet " +
				"FROM zimbra_invitee zi, meetings m " +
				"WHERE zi.id = ? " +
				"AND zi.name = m.name " +
				"AND zi.description = m.description "+
				"AND m.created_by = ? " +
				"AND m.deleted = 0 " +
				"AND DATE_FORMAT(date(m.date_start), '%m-%d-%Y') = DATE_FORMAT(m.date_start, '%m-%d-%Y')";
		LogInfo.T("SQL :: "+sql);
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, idMeet);
			st.setString(2, user.getIdSugar());
			ResultSet rs = st.executeQuery();
			return rs;
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}
		return null;

	}
		
	public CreateAppointmentRequest getMeet(String idMeet, User user) {
			this.periodic = false;
		
		actualMeet = null;
		try{

			ResultSet rs = this.gettMeetModifiedFromDB(idMeet, user);

			User userOrganizator = new User();
			if (rs.next()) {
				this.createdBy = rs.getString("created_by");
				this.location = rs.getString("location");
				this.description = rs.getString("description");
				this.name_invite = rs.getString("name_invite");

				String periodic = rs.getString("repeat_type");
				if ((periodic != null) && (!periodic.equalsIgnoreCase("NULL"))
						&& (!periodic.equals(""))) {
					this.periodic = true;
				}

				LogInfo.T("Reunion Obtenida OK ");
				actualMeet = rs.getString("user_id");
				LogInfo.T("Organizador " + user.getIdSugar());
				userOrganizator.setIdSugar(user.getIdSugar());
				userOrganizator.setUserName(user.getUserName());
				LogInfo.T("Organizador User " + user.getUserName());
				String mail = getMail(user.getIdSugar(), "Users");
				LogInfo.T("Organizador Mail " + mail);
				userOrganizator.setMail(mail);

				String ids = rs.getString("user_invitees");

				String[] idInvitee = ids.split(",");
				mail = "";

				List<String> mails = new ArrayList<String>();
				
				for (String idInv : idInvitee) {
					mail = getMail(idInv, "Users");
					if (mail == null || mail.length()<3) {
						continue;
					}
					mails.add(mail);
				}

				ids = rs.getString("contact_invitees");

				String[] contactsInvitee = ids.split(",");
				mail = "";

				for (String idInv : contactsInvitee) {
					if (idInv == "") {
						continue;
					}
					mail = getMail(idInv, "Contacts");
					if ((mail == null) || mail.length()<3) {
						continue;
					}
					mails.add(mail);
				}

				ids = rs.getString("lead_invitees");

				String[] leadsInvitee = ids.split(",");
				mail = "";

				for (String idInv : leadsInvitee) {
					if (idInv == "") {
						continue;
					}
					mail = getMail(idInv, "Leads");
					if (mail == null || mail.length()<3 ) {
						continue;
					}
					mails.add(mail);
				}

				InvitationInfo inv = new InvitationInfo();
				inv.setName(rs.getString("name"));
				inv.setLoc(this.location);
				inv.setFb("B");
				inv.setTransp("0");

				inv.setStatus("CONF");
				inv.setAllDay(false);
				inv.setDraft(false);

				
				List<CalendarAttendee> cas = new ArrayList<CalendarAttendee>();
				List<EmailAddrInfo> emailAddrs = new ArrayList<EmailAddrInfo>();

				EmailAddrInfo emailOrg = new EmailAddrInfo();
				emailOrg.setA(userOrganizator.getMail());
				emailOrg.setT("t");

				emailAddrs.add(emailOrg);
				
				if(inv.getLoc() != null && inv.getLoc().length() >0
						&& inv.getLoc().contains("@")
						){
					CalendarAttendee ca = new CalendarAttendee();
					ca.setA(inv.getLoc());
					ca.setRole("REQ");
					ca.setPtst("NE");
					ca.setRsvp(true);
					ca.setCutype("RES");

					cas.add(ca);

					EmailAddrInfo ea = new EmailAddrInfo();
					ea.setA(inv.getLoc());
					ea.setT("t");

					emailAddrs.add(ea);
					
				}

				for (String email : mails) {
					CalendarAttendee ca = new CalendarAttendee();
					ca.setA(email);
					ca.setRole("REQ");
					ca.setPtst("NE");
					ca.setRsvp(true);

					cas.add(ca);

					EmailAddrInfo ea = new EmailAddrInfo();
					ea.setA(email);
					ea.setT("t");

					emailAddrs.add(ea);
				}
				//citas periodicas
				String period = rs.getString("repeat_type");
				if( period != null && period.length() > 1){
					LogInfo.T("Cita periodica "+period);
					Frecuencia freq;
					if(period.equalsIgnoreCase("Daily")){
						freq = Frecuencia.DIARIO;
					}else if(period.equalsIgnoreCase("Weekly")){
						freq = Frecuencia.SEMANAL;
					}else{
						freq = Frecuencia.MENSUAL;
					}
					RecurrenceInfo ri = new RecurrenceInfo();
										
					AddRecurrenceInfo add = new AddRecurrenceInfo();
					
					SimpleRepeatingRule rule = new SimpleRepeatingRule();
					IntervalRule interval = new IntervalRule();
					interval.setIval(1);				
					rule.setInterval(interval);
					
					//interval
					String repeat = rs.getString("repeat_dow");
					
					if(repeat != null){
						ByDayRule byd = new ByDayRule();
				
						
						if(repeat.contains("0")){
							WkDay wd = new WkDay();
							wd.setDay("SU");
							byd.addDay(wd);
						}
						if(repeat.contains("1")){
							WkDay wd = new WkDay();
							wd.setDay("MO");
							byd.addDay(wd);
						}
						if(repeat.contains("2")){
							WkDay wd = new WkDay();
							wd.setDay("TU");
							byd.addDay(wd);
						}
						if(repeat.contains("3")){
							WkDay wd = new WkDay();
							wd.setDay("WE");
							byd.addDay(wd);
						}
						if(repeat.contains("4")){
							WkDay wd = new WkDay();
							wd.setDay("TH");
							byd.addDay(wd);
						}
						if(repeat.contains("5")){
							WkDay wd = new WkDay();
							wd.setDay("FR");
							byd.addDay(wd);
						}
						if(repeat.contains("6")){
							WkDay wd = new WkDay();
							wd.setDay("SA");
							byd.addDay(wd);
						}
						
						
						rule.setByday(byd);
					}
					
					rule.setFreq(freq.getFrecuencia());//repeat_until
					
					String until = rs.getString("repeat_until");
					if(until != null && until.length() > 4){
						DateTimeStringAttr df = new DateTimeStringAttr();
						String ut = Util.getMeetingRecurDate(until);
						df.setD(ut);
						LogInfo.T("fecha fin "+ut);
						rule.setUntil(df);
					}else{
						String cnt = rs.getString("repeat_count");
						NumAttr na = new NumAttr();
						na.setNum(Integer.parseInt(cnt));
						rule.setCount(na);
					}
				
					add.addObj(rule);
					
					ri.addObj(add);
					
					inv.setRecur(ri);
				}
				
				//alarma
				inv.setAt(cas);
				List<AlarmInfo>  lai = new ArrayList<AlarmInfo>();

				AlarmInfo ai = new AlarmInfo();
				ai.setAction("DISPLAY");
				DurationInfo di = new DurationInfo();
				di.setNeg(true);
				di.setM(5);
				di.setRelated("START");

				AlarmTriggerInfo aif = new AlarmTriggerInfo();
				aif.setRel(di);

				ai.setTrigger(aif);

				lai.add(ai);

				inv.setAlarm(lai);

				CalOrganizer org = new CalOrganizer();

				org.setA(userOrganizator.getMail());
				org.setD(userOrganizator.getUserName());

				inv.setOr(org);

				DtTimeInfo dateStart = new DtTimeInfo();
				Calendar c = Util.convertToCalMeet(rs.getString("date_start"));
				dateStart.setD(Util.getMeetingDate(c));

				inv.setS(dateStart);

				DtTimeInfo dateEnd = new DtTimeInfo();
				c = Util.convertToCalMeet(rs.getString("date_end"));

				dateEnd.setD(Util.getMeetingDate(c));
				inv.setE(dateEnd);

				Msg m = new Msg();
				m.setInv(inv);

				MimePartInfo mp = new MimePartInfo();
				mp.setCt("text/plain");
				mp.setContent(Util.covertUTF8(rs.getString("description")));
				//mp.setContent(rs.getString("description"));
				m.setMp(mp);

				m.setL("10");
				m.setE(emailAddrs);

				CreateAppointmentRequest ar = new CreateAppointmentRequest();
				ar.setM(m);
				LogInfo.T("Bean Reunion Creado:: " + ar);
				return ar;
			}
			return null;
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

			return null;
		
	}

	public CancelAppointmentRequest getDeletedMeet(String idMeet, User user) {

		LogInfo.T("Obteniendo Reunion zimbra_invitee para eliminacion:: ");

		try{
			ResultSet rs = this.gettMeetModifiedFromDB(idMeet, user);

			if (rs != null && rs.next()) {
				LogInfo.T("Reunion Obtenida OK ");
				String ids = rs.getString("user_invitees");
				List<EmailAddrInfo> emailAddrs = new ArrayList<EmailAddrInfo>();

				String idInvitee[] = ids.split(",");
				String mail = "";

				List<String> mails = new ArrayList<String>();
				for (String idInv : idInvitee) {
					mail = this.getMail(idInv, "Users");
					if (mail == null) {
						continue;
					} else {
						mails.add(mail);
					}

				}

				ids = rs.getString("contact_invitees");

				String contactsInvitee[] = ids.split(",");
				mail = "";

				for (String idInv : contactsInvitee) {
					if (idInv == "") {
						continue;
					}
					mail = this.getMail(idInv, "Contacts");
					if (mail == null || mail == "") {
						continue;
					} else {
						mails.add(mail);
					}

				}

				ids = rs.getString("lead_invitees");

				String leadsInvitee[] = ids.split(",");
				mail = "";

				for (String idInv : leadsInvitee) {
					if (idInv == "") {
						continue;
					}
					mail = this.getMail(idInv, "Leads");
					if (mail == null || mail == "") {
						continue;
					} else {
						mails.add(mail);
					}

				}

				for (String email : mails) {
					EmailAddrInfo ea = new EmailAddrInfo();
					ea.setA(email);
					ea.setT("t");

					emailAddrs.add(ea);
				}

				Msg m = new Msg();
				m.setSu("Cancelado: " + rs.getString("name"));

				MimePartInfo mp = new MimePartInfo();
				mp.setCt("text/plain");
				mp.setContent(rs.getString("description"));

				m.setMp(mp);
				m.setE(emailAddrs);

				CancelAppointmentRequest apr = new CancelAppointmentRequest();
				apr.setComp(0);
				apr.setId(rs.getString("outlook_id"));
				apr.setM(m);

				return apr;
			} else {
				String sql = "SELECT mt.* FROM meetings mt " + "WHERE mt.id = ? ";
				Connection conn = getConnection();
				conn.setAutoCommit(true);
				PreparedStatement st = conn.prepareStatement(sql);
				st = conn.prepareStatement(sql);
				st.setString(1, idMeet);

				rs = st.executeQuery();
				if (rs.next()) {

					Msg m = new Msg();
					m.setSu("Cancelado: " + rs.getString("name"));

					MimePartInfo mp = new MimePartInfo();
					mp.setCt("text/plain");
					mp.setContent(rs.getString("description"));

					m.setMp(mp);
					CancelAppointmentRequest apr = new CancelAppointmentRequest();
					apr.setComp(0);
					apr.setId(rs.getString("outlook_id"));
					apr.setM(m);

					return apr;
				}
			}
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

		return null;
	}

	public ModifyAppointmentRequest getModifiedMeet(String idMeet, User user) {

		LogInfo.T("Obteniendo Reunion zimbra_invitee para modificacion:: ");
		try{

			ResultSet rs = this.gettMeetModifiedFromDB(idMeet, user);

			User userOrganizator = new User();
			if (rs.next()) {
				LogInfo.T("Reunion Obtenida OK ");
				LogInfo.T("Organizador " + user.getIdSugar());
				userOrganizator.setIdSugar(user.getIdSugar());
				userOrganizator.setUserName(user.getUserName());
				LogInfo.T("Organizador User " + user.getUserName());
				String mail = this.getMail(user.getIdSugar(), "Users");
				LogInfo.T("Organizador Mail " + mail);
				userOrganizator.setMail(mail);

				String ids = rs.getString("user_invitees");

				String idInvitee[] = ids.split(",");
				mail = "";

				List<String> mails = new ArrayList<String>();
				for (String idInv : idInvitee) {
					mail = this.getMail(idInv, "Users");
					if (mail == null) {
						continue;
					} else {
						mails.add(mail);
					}

				}

				ids = rs.getString("contact_invitees");

				String contactsInvitee[] = ids.split(",");
				mail = "";

				for (String idInv : contactsInvitee) {
					if (idInv == "") {
						continue;
					}
					mail = this.getMail(idInv, "Contacts");
					if (mail == null || mail == "") {
						continue;
					} else {
						mails.add(mail);
					}

				}

				ids = rs.getString("lead_invitees");

				String leadsInvitee[] = ids.split(",");
				mail = "";

				for (String idInv : leadsInvitee) {
					if (idInv == "") {
						continue;
					}
					mail = this.getMail(idInv, "Leads");
					if (mail == null || mail == "") {
						continue;
					} else {
						mails.add(mail);
					}

				}
				InvitationInfo inv = new InvitationInfo();
				inv.setName(rs.getString("name"));
				inv.setLoc(rs.getString("location"));
				inv.setFb("B");
				inv.setTransp("0");

				inv.setStatus("CONF");
				inv.setAllDay(false);
				inv.setDraft(false);

				List<CalendarAttendee> cas = new ArrayList<CalendarAttendee>();
				List<EmailAddrInfo> emailAddrs = new ArrayList<EmailAddrInfo>();

				EmailAddrInfo emailOrg = new EmailAddrInfo();
				emailOrg.setA(userOrganizator.getMail());
				emailOrg.setT("t");

				emailAddrs.add(emailOrg);

				if (inv.getLoc() != null && inv.getLoc().length() > 0) {
					CalendarAttendee ca = new CalendarAttendee();
					ca.setA(inv.getLoc());
					ca.setRole("REQ");
					ca.setPtst("NE");
					ca.setRsvp(true);
					ca.setCutype("RES");

					cas.add(ca);

					EmailAddrInfo ea = new EmailAddrInfo();
					ea.setA(inv.getLoc());
					ea.setT("t");

					emailAddrs.add(ea);

				}

				for (String email : mails) {
					CalendarAttendee ca = new CalendarAttendee();
					ca.setA(email);
					ca.setRole("REQ");
					ca.setPtst("NE");
					ca.setRsvp(true);

					cas.add(ca);

					EmailAddrInfo ea = new EmailAddrInfo();
					ea.setA(email);
					ea.setT("t");

					emailAddrs.add(ea);
				}

				inv.setAt(cas);
				
				//citas periodicas
				String period = rs.getString("repeat_type_meet");
				if( period != null && period.length() > 1){
					LogInfo.T("Cita periodica "+period);
					Frecuencia freq;
					if(period.equalsIgnoreCase("Daily")){
						freq = Frecuencia.DIARIO;
					}else if(period.equalsIgnoreCase("Weekly")){
						freq = Frecuencia.SEMANAL;
					}else{
						freq = Frecuencia.MENSUAL;
					}
					RecurrenceInfo ri = new RecurrenceInfo();
										
					AddRecurrenceInfo add = new AddRecurrenceInfo();
					
					SimpleRepeatingRule rule = new SimpleRepeatingRule();
					IntervalRule interval = new IntervalRule();
					interval.setIval(1);				
					rule.setInterval(interval);
					
					rule.setFreq(freq.getFrecuencia());//repeat_until
					
					//interval
					String repeat = rs.getString("repeat_dow");
					
					if(repeat != null){
						ByDayRule byd = new ByDayRule();
				
						
						if(repeat.contains("0")){
							WkDay wd = new WkDay();
							wd.setDay("SU");
							byd.addDay(wd);
						}
						if(repeat.contains("1")){
							WkDay wd = new WkDay();
							wd.setDay("MO");
							byd.addDay(wd);
						}
						if(repeat.contains("2")){
							WkDay wd = new WkDay();
							wd.setDay("TU");
							byd.addDay(wd);
						}
						if(repeat.contains("3")){
							WkDay wd = new WkDay();
							wd.setDay("WE");
							byd.addDay(wd);
						}
						if(repeat.contains("4")){
							WkDay wd = new WkDay();
							wd.setDay("TH");
							byd.addDay(wd);
						}
						if(repeat.contains("5")){
							WkDay wd = new WkDay();
							wd.setDay("FR");
							byd.addDay(wd);
						}
						if(repeat.contains("6")){
							WkDay wd = new WkDay();
							wd.setDay("SA");
							byd.addDay(wd);
						}
						
						
						rule.setByday(byd);
					}
					
					
					String until = rs.getString("repeat_until_meet");
					if(until != null && until.length() > 4){
						DateTimeStringAttr df = new DateTimeStringAttr();
						String ut = Util.getMeetingRecurDate(until);
						df.setD(ut);
						LogInfo.T("fecha fin "+ut);
						rule.setUntil(df);
					}else{
						String cnt = rs.getString("repeat_count_meet");
						NumAttr na = new NumAttr();
						na.setNum(Integer.parseInt(cnt));
						rule.setCount(na);
					}
				
					add.addObj(rule);
					
					ri.addObj(add);
					
					inv.setRecur(ri);
				}
				
				List<AlarmInfo> lai = new ArrayList<AlarmInfo>();
				
				AlarmInfo ai = new AlarmInfo();
				ai.setAction("DISPLAY");
				DurationInfo di = new DurationInfo();
				di.setNeg(true);
				di.setM(10);
				di.setRelated("START");

				AlarmTriggerInfo aif = new AlarmTriggerInfo();
				aif.setRel(di);

				ai.setTrigger(aif);

				lai.add(ai);

				inv.setAlarm(lai);

				CalOrganizer org = new CalOrganizer();

				org.setA(userOrganizator.getMail());
				org.setD(userOrganizator.getUserName());

				inv.setOr(org);

				DtTimeInfo dateStart = new DtTimeInfo();
				Calendar c = Util.convertToCalMeet(rs.getString("date_start"));
				dateStart.setD(Util.getMeetingDate(c));

				inv.setS(dateStart);

				DtTimeInfo dateEnd = new DtTimeInfo();
				c = Util.convertToCalMeet(rs.getString("date_end"));

				dateEnd.setD(Util.getMeetingDate(c));
				inv.setE(dateEnd);

				Msg m = new Msg();

				MimePartInfo mp = new MimePartInfo();
				mp.setCt("text/plain");
				mp.setContent(rs.getString("description"));
				m.setMp(mp);

				m.setInv(inv);
				m.setL("10");
				m.setE(emailAddrs);

				ModifyAppointmentRequest ar = new ModifyAppointmentRequest();
				ar.setM(m);
				ar.setId(rs.getString("outlook_id"));
				LogInfo.T("Bean Reunion Modificacion Creado:: " + ar);
				return ar;
			}
			return null;
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

		return null;

	}

	public void deleteTempMeet(String idMeet, CancelAppointmentRequest meet) {
		LogInfo.T("Eliminando Reunion:: ");
		String sql = "SELECT repeat_type FROM zimbra_invitee WHERE id = ? ";
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, idMeet);
			ResultSet rs = st.executeQuery();
			if(rs.getString("repeat_type") != null && !rs.getString("repeat_type").equals("NULL")){
				sql = "DELETE FROM meetings WHERE outlook_id = ? ";
				
				st = conn.prepareStatement(sql);
				st.setString(1, meet.getId());
				st.executeUpdate();
				
			}
			sql = "DELETE FROM zimbra_invitee WHERE id = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, idMeet);
			st.executeUpdate();
			
			sql = "DELETE FROM meet WHERE id = ? ";
			
			LogInfo.T("Reunion Eliminada OK:: ");

		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}
	}

	public boolean updateMeet(String idZimbra, String idSugar, boolean isPeriodic, String nameInv ) {
		boolean resp = false;

		String sql = "UPDATE meetings SET type = '" + NotaZimbra.TYPE + "', "
				+ "outlook_id = ?, " + "status = 'Held', " + "deleted = '0' "
				+ "WHERE id= ? ";
		

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			st = conn.prepareStatement(sql);

			st.setString(1, idZimbra);
			st.setString(2, idSugar);
			st.executeUpdate();
			
			if(isPeriodic){
				sql = "UPDATE meetings SET type = '" + NotaZimbra.TYPE + "' "
						+ "WHERE name = ? ";
				st = conn.prepareStatement(sql);
				st.setString(1, nameInv);
				st.executeUpdate();
			}

		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

		return resp;
	}

	private boolean updateMeetInfo(String idMeetSugar,
			InviteComponentWithGroupInfo inv) {

		String name = inv.getName();
		String desc = inv.getDesc();
		// DtTimeInfo ds = inv.getS();
		// DtTimeInfo de = inv.getE();
		// String startDate = ds.getD();
		// String endDate = de.getD();
		boolean resp = false;
		// LogInfo.T("actualizando ::"+idMeetSugar+" dates :: "+startDate +
		// " "+endDate);
		String sql = "UPDATE meetings SET " + "name = ?, " + "description = ? "
		// +"date_start = STR_TO_DATE( ?,'%Y%m%dT%H%i%s'), "
		// +"date_end = STR_TO_DATE(?,'%Y%m%dT%H%i%s') "
				+ "WHERE id= ? ";
		LogInfo.T("actualizando :: " + idMeetSugar);
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			// conn.setAutoCommit( false );
			st = conn.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, desc);
			// st.setString(3 , startDate);
			// st.setString(4 , endDate);
			st.setString(3, idMeetSugar);
			st.executeUpdate();

		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}

		return resp;
	}

	public String getToken(User u) {
		String sql = "SELECT token_id FROM zimbra_auth WHERE user_id = ? ";
		String tk = "";
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			st = conn.prepareStatement(sql);

			st.setString(1, u.getIdSugar());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				tk = rs.getString("token_id");
			}
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
			closeResources();
		}
		return tk;
	}

}