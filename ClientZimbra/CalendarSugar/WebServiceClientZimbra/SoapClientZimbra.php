<?php
include_once ('ExceptionZimbra.php');

class SoapClientZimbra
{

    // The entire XML message
    private $message;
    // Pointing to the context element
    private $context;
    // used for generating the filename of xml log dump
    private $lastRequestName;

    private $curlHandle;

    public function __construct()
    {
        $this->curlHandle = curl_init();
        //curl_setopt($this->curlHandle, CURLOPT_URL, "https://laumail.laumayer.com:7071/service/admin/soap");
		curl_setopt($this->curlHandle, CURLOPT_URL, "https://laumail.laumayer.com/service/wsdl/ZimbraService.wsdl");
        curl_setopt($this->curlHandle, CURLOPT_POST, TRUE);
        curl_setopt($this->curlHandle, CURLOPT_RETURNTRANSFER, TRUE);
        curl_setopt($this->curlHandle, CURLOPT_SSL_VERIFYPEER, FALSE);
        curl_setopt($this->curlHandle, CURLOPT_SSL_VERIFYHOST, FALSE);
        curl_setopt($this->curlHandle, CURLOPT_CONNECTTIMEOUT, 120);
		curl_setopt($curl, CURLOPT_VERBOSE, true);
			
		curl_setopt($curl, CURLOPT_HEADER, FALSE);
	    curl_setopt($curl, CURLOPT_HTTPHEADER, array("Content-Type: text/xml;charset=UTF-8", 
								'SOAPAction: "urn:zimbraAccount/Auth"',
								"Host: laumail.laumayer.com:443",
								"Content-Length: 370",
								"Connection: Keep-Alive",
								"Expect: 100-continue"
								));
		//curl_setopt($curl, CURLOPT_HTTPHEADER, $this->headers);
			curl_setopt($curl, CURLOPT_TIMEOUT, 120);


        $this->message = new \SimpleXMLElement('<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"></soap:Envelope>');
        $this->context = $this->message->addChild('Header')->addChild('context', null, 'urn:zimbra');
        $this->message->addChild('Body');
    }

    public function getXml()
    {	
		$xml = $this->message->asXml();
		var_dump($xml);
        return $xml;
    }

    public function addContextChild($name, $value)
    {
        if (isset($this->context->$name)) {
            $this->context->$name = $value;
        } else {
            $this->context->addChild($name, $value);
        }
    }

    public function request($name, $attributes = array(), $params = array())
    {
        $this->lastRequestName = $name;
        unset($this->message->children('soap', true)->Body);
        $body = $this->message->addChild('Body');
        $newChild = $body->addChild($name, null, 'urn1:zimbraAccount');

        foreach ($attributes as $key => $value) {
            $newChild->addAttribute($key, $value);
        }

        foreach ($params as $key => $value) {
            if (is_array($value)) {
                switch ($key) {
                    case 'attributes':
                        foreach ($value as $l => $b) {
                            $attributes = (array) $b;   // multiple attributes support
                            foreach ($attributes as $attribute) {
                                $newParam = $newChild->addChild('a', $attribute);
                                $newParam->addAttribute('n', $l);
                            }
                        }
                        break;
                    default:
                        $newParam = $newChild->addChild($key, $value['_']);
                        unset($value['_']);
                        foreach ($value as $l => $b) {
                            $newParam->addAttribute($l, $b);
                        }
                }
            } else {
                $newChild->addChild($key, $value);
            }
        }

        curl_setopt($this->curlHandle, CURLOPT_POSTFIELDS, $this->getXml());
        return $this->processReply(curl_exec($this->curlHandle));
    }

    private function processReply($soapMessage)
    {
	var_dump($soapMessage);
        if (!$soapMessage) {
            throw new \Exception(curl_error($this->curlHandle), curl_errno($this->curlHandle));
        }

        $xml = new \SimpleXMLElement($soapMessage);

        $fault = $xml->children('soap', true)->Body->Fault;
        if ($fault) {
            throw new ExceptionZimbra($fault->Detail->children()->Error->Code);
        }

        return $xml->children('soap', true)->Body;
    }

}
