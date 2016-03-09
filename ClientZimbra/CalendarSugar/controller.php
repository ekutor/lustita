<?php
if(!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point');

require_once("modules/Calendar/controller.php");
require_once('custom/modules/Calendar/SyncZymbra.php');

class CustomCalendarController extends CalendarController
{
	function action_synczimbra()
	{	

		$sync = new SyncZymbra();

		$userId = $_SESSION['authenticated_user_id'];
		$token = $_SESSION['zimbraAuthToken'];
		if(!isset($token)){
		
			$objConnection = &DBManagerFactory::getInstance();
			$sql = "SELECT token_id FROM zimbra_auth WHERE user_id = '$userId'";
			$objQuery = $objConnection->query($sql);
			if( $row = $objConnection->fetchByAssoc($objQuery)) {
				$_SESSION['zimbraAuthToken'] = $row['token_id'];
			}
		}
		
		$sync->syncZimbra();
		
		$queryParams = array(
								'module' => 'Calendar',
								'action' => 'index',
								
							);

		SugarApplication::redirect('index.php?' . http_build_query($queryParams));
	}
	
	 protected function action_saveactivity(){
	 /*$arr = array(
				'access' => 'yes',
				'type' => strtolower($bean->object_name),
				'module_name' => $bean->module_dir,
				'user_id' => $GLOBALS['current_user']->id,
				'detail' => 1,
				'edit' => 1,
				'name' => $bean->name,
				'record' => $bean->id,
				'users' => $user_ids,
			);*/
			
		parent::action_saveactivity();
		$sync = new SyncZymbra();
		$idBean = $this->view_object_map['jsonData']['record'];
		/*foreach ($_REQUEST as $clave => $valor) {
			$sync->log("REQ:: CLave: ".$clave." =".$valor);
		}
		foreach ($this->view_object_map['jsonData'] as $clave => $valor) {
			$sync->log("REQ:: CLave: ".$clave." =".$valor);
		}*/
		//si es vacio es nueva sino es modificacion
		if(empty($_REQUEST['record'])){
			$sync->createZimbraMet($idBean,1);
		}else{
			$sync->createZimbraMet($idBean,2);
		}
			
	}
	
	 protected function action_remove(){
				
		parent::action_remove();
		$sync = new SyncZymbra();
		$idBean = $_REQUEST['record'];
		/*foreach ($_REQUEST as $clave => $valor) {
			$sync->log("REQ:: CLave: ".$clave." =".$valor);
		}*/
		$sync->deleteMet($idBean);
			
	}

}
