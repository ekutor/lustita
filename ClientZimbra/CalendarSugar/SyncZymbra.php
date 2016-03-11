<?php
include ('WebServiceClientZimbra/Admin.php');
//include_once('WebServiceClientZimbra/MeetingDTO.php');
include ('CalendarDTO.php');
//include ('modules/Meetings/Meeting.php');


class SyncZymbra 
{
	var $file;
	var $path;
	
  public function __construct() {
    $this->path = "/var/www/html/crm/custom/modules/Calendar/";
	$this->file = fopen($this->path."zimbraSinc.log", "a") or die("Unable to open file!");

  }
 
   
   public function syncZimbra($user = null,$passw = null)
    {	
        global $sugar_config;
		$userId = $_SESSION['authenticated_user_id'];
		$usuario = $_SESSION['login_user_name'];
		$path = $this->path.'ClientZimbra.jar';
		$this->log("sincronizando::");
		
		$this->log("userid::".$userId);
		
		$this->log("usuario::".$usuario);
		if(empty($usuario)){
			$usuario = "default";
		}
		
		if(empty($_SESSION['zimbraAuthToken'])){
			$this->log(" datos enviados: 1 $userId $usuario $user $passw  $path");
			$db = &DBManagerFactory::getInstance();			
			$this->savePS($userId, $user , $db, $passw);
			exec("/usr/bin/java -Djava.awt.headless=true -jar $path 1 $userId $usuario $user $passw | $this->path.log.txt &", $output);
		
			$this->log("autenticado OK ::");
		}else{
		$this->log("autenticado por token ::");
			$token = isset($_SESSION['zimbraAuthToken']);
			$this->log(" datos enviados: $path 2 $userId $usuario $token ");
			exec("/usr/bin/java -Djava.awt.headless=true -jar  $path 2 $userId $usuario $token ", $output);
			
			$queryParams = array(
					'module' => "Calendar",
					'action' => 'index',
					
				);

		SugarApplication::redirect('index.php?' . http_build_query($queryParams));
		}
		$this->view = "";

			
	}
	
	public function createZimbraMet($sugarID, $typeMet)
    {	
		$this->log("createZimbraMet CReando Reunion");
		$path = $this->path.'ClientZimbra.jar';
		$db = &DBManagerFactory::getInstance();
		$userId = $_SESSION['authenticated_user_id'];
		$usuario = $_SESSION['login_user_name'];
		$token = $this->getToken($userId, $db);
		
		if(empty($usuario)){
			$usuario = "default";
		}
	    $beanID = "";
		$modulo = "Users";
		
		$invitates = array();
		
		
		$this->log("Usuario :: $usuario");
		$user_invitees = rtrim( $_REQUEST['user_invitees'], ",");
		
		
		$modulo = "Contacts";
		$contact_invitees = rtrim( $_REQUEST['contact_invitees'], ",");
		
		
		$modulo = "Leads";
		$lead_invitees = rtrim( $_REQUEST['lead_invitees'], ",");
		
		$mt->invitates = $invitates;
		$dateStart = $_REQUEST['date_start'];
		$dateEnd = $_REQUEST['date_end'];
		

		$name = $_REQUEST['name'];
		$location = $_REQUEST['location'];
		$descr = $_REQUEST['description'];
		
		//adicion de repeticiones periodicas
		$repeat_type = $_REQUEST['repeat_type'];
		$repeat_interval = $_REQUEST['repeat_interval'];
		$repeat_count = $_REQUEST['repeat_count'];
		$repeat_until = $_REQUEST['repeat_until'];
		
		$idmeet = create_guid();
		
		$sql = "INSERT INTO `zimbra_invitee`(`user_invitees`, `contact_invitees`, `lead_invitees`, `date_start`, `date_end`, `name`, ";
			$sql .="`location`, `description`, `user_id`, `id`, repeat_type, repeat_interval, repeat_count, repeat_until ) ";
			$sql .="VALUES ( '$user_invitees','$contact_invitees','$lead_invitees','$dateStart','$dateEnd',";
			$sql .="'$name','$location','$descr','$sugarID',";
			$sql .="'$repeat_type', '$repeat_interval', '$repeat_count', '$repeat_until' )";
			$this->log("Reunion Creada ". $sql);
			
		if($typeMet == 1){
			$objQuery = $db->query($sql);
			
			$this->log("Datos enviados "."$path 3 userid= $userId  user= $usuario token= $token meet= $idmeet");
			exec("/usr/bin/java -Djava.awt.headless=true -jar  $path 3 $userId $usuario $token $idmeet", $output);
		}else{
			$objQuery = $db->query("DELETE FROM zimbra_invitee WHERE user_id = '$sugarID'");
			$objQuery = $db->query($sql);
			$this->log("Datos enviados "."$path 5 userid= $userId  user= $usuario token= $token meet= $idmeet");
			exec("/usr/bin/java -Djava.awt.headless=true -jar  $path 5 $userId $usuario $token $idmeet", $output);
		}
		
		
		return $idmeet;
	}
	
	public function deleteMet($sugarID)
    {	
		$this->log("Eliminando Reunion");
		$path = $this->path.'ClientZimbra.jar';
		$db = &DBManagerFactory::getInstance();
		$userId = $_SESSION['authenticated_user_id'];
		$usuario = $_SESSION['login_user_name'];
		$token = $this->getToken($userId, $db);
		$this->log("Datos enviados "."$path 6 userid= $userId  user= $usuario token= $token sugarID= $sugarID");
		exec("/usr/bin/java -Djava.awt.headless=true -jar  $path 6 $userId $usuario $token $sugarID", $output);
	}
	
	public function getMailFromDB($modulo, $beanID, $connDB){
		$sql = "SELECT em.email_address FROM  email_addr_bean_rel emrel,email_addresses em
		WHERE  emrel.bean_id='$beanID'
		AND emrel.bean_module = '$modulo'
		AND em.id = emrel.email_address_id";
		$re = $connDB->query($sql);
		$this->log("getMailFromDB: sql = $sql");
		$contacts_rel_arr = array();
		$mail = '';
		while($ro = $connDB->fetchByAssoc($re)){
			$mail = $ro['email_address'];
			$this->log("getMailFromDB: mail = $mail");
			break;
		}
		return $mail;
	}
	
	
	public function getToken($userID, $connDB){
		$token = $_SESSION['zimbraAuthToken'] ;
		$this->log("getToken: $userID   Token :: $token");
		if(empty($token)){
			$sql = "SELECT token_id FROM  zimbra_auth WHERE  user_id='$userID'";
			$re = $connDB->query($sql);
			$this->log("getToken: sql = $sql");
			$contacts_rel_arr = array();
			$token = '';
			while($ro = $connDB->fetchByAssoc($re)){
				$token = $ro['token_id'];
				break;
			}
		     $this->log("getToken: Token DB :: $token");
			$_SESSION['zimbraAuthToken']  = $token;
		}
		return $token;
	}
	
	public function savePS($userID, $usuario , $connDB, $ps){

		$this->log("savePS: $userID ");
		if(!empty($userID)){
			$sql = "SELECT user_id FROM  zimbra_auth WHERE  user_id='$userID'";
			$re = $connDB->query($sql);
			if($ro = $connDB->fetchByAssoc($re)){
				$sql = "UPDATE zimbra_auth SET ps = '$ps' WHERE user_id='$userID'";
				$re = $connDB->query($sql);

			}else{
				$sql = "INSERT INTO zimbra_auth (token_id,user_id,user_name, ps) values ('0','$userID','$usuario', '$ps')";
				$re = $connDB->query($sql);
			}

		}
	}
	
	public function showInfoRequest(){
			$this->log("****INFO REQUEST BEGIN****");
			foreach($_REQUEST as $nombre_campo => $valor){
				$this->log("REQUEST CAMPO: ".$nombre_campo." VALOR: ".$valor);
			}
			$this->log("****INFO REQUEST END****");
			$this->log("****INFO POST BEGIN****");
			foreach($_POST as $nombre_campo => $valor){
				$this->log("POST CAMPO: ".$nombre_campo." VALOR: ".$valor);
			}
			$this->log("****INFO POST END****");
	}
	
	
	public function getDate($mil) {
		//date_default_timezone_set("America/Bogota");
		$seconds = $mil / 1000;
		$date = date("m-d-Y H:i:s", $seconds);
		return $date;
	}
	
	public function getInitDate() {
		date_default_timezone_set('America/Bogota');
		$month = date("m", time());
		$year = date("Y", time());
		if($month == 1){
			$month = 12;
			$year--;
		}else{
			$month--;
		}
		$di=mktime(0,0,0, $month, 01, $year ) * 1000;
		
		$this->log("date Inicial ".$di);
		return $di;
	}
	
	public function getEndDate() {		
		date_default_timezone_set('America/Bogota');
		//$date = date('m/d/Y h:i:s a', time());
		$month = date("m", time());
		$year = date("Y", time());
		if($month == 12){
			$month = 1;
			$year++;
		}else{
			$month ++;
		}
		$d = new DateTime( "$year-$month-01" ); 
		$lastDayMonth = $d->format( 't' );
		
		$di=mktime(23,59,0, $month, $lastDayMonth , $year ) * 1000;
		
		$this->log("date Final ".$di);
		return $di;
	}
	
	//Formato Zimbra 20150928T130000
	public function convertDateMeeting($dateString) {	
		$this->log("convertDateMeeting recibido:".$dateString);	
		date_default_timezone_set('America/Bogota');
		$fecha = DateTime::createFromFormat('m/j/Y h:ia', $dateString);
		$fecha->setTimeZone(new DateTimeZone('America/Bogota'));
		$ff = $fecha->format('Ymd');
		$hf = $fecha->format('His');
		$fecha = $ff."T".$hf;
		$this->log("date Reunion:".$fecha);
		return $fecha;
	}
	
	
	public function getNowinMills() {
		$timemills = round(microtime(true) * 1000);
		return $timemills;
	}
	
	public function log($message){
		fwrite($this->file, "\n".$message);
	}
}
