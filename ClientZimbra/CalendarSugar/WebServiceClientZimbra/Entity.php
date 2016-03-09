<?php

/**
 * A basic Zimbra object.
 *
 * @author LiberSoft <info@libersoft.it>
 * @author Chris Ramakers <chris.ramakers@gmail.com>
 * @license http://www.gnu.org/licenses/gpl.txt
 */

namespace Zimbra\ZCS;

class Entity
{

    private $data = array();

    public function __construct($object)
    {
        $this->data['id'] = (string) $object['id'];
        $this->data['name'] = (string) $object['name'];

        foreach ($object->children()->a as $data) {
            $key = (string) $data['n'];

            if (isset($this->data[$key])) {     // store multiple attributes in an array
                $this->data[$key] = (array) $this->data[$key];
                $this->data[$key][] = self::convert($data);
            } else {
                $this->data[$key] = self::convert($data);
            }
        }
    }

    private static function convert($data) {
        switch ($data) {
            case 'FALSE':
                return false;
            case 'TRUE':
                return true;
            default:
                return (string) $data;
        }
    }

    public function __toString()
    {
        return $this->data['name'];
    }

    public function __get($name)
    {
        if (array_key_exists($name, $this->data)) {
            return $this->data[$name];
        }
    }

    public function get($name)
    {
        return $this->__get($name);
    }

    public function __set($name, $value)
    {
        $this->data[$name] = $value;
    }

    public function set($name, $value)
    {
        $this->__set($name, $value);
    }

    public function toArray()
    {
        return $this->data;
    }

    public function getAttributes()
    {
        return array_keys($this->data);
    }

}
