<?php

/**
 * Generate an entity object that provide CRUD operation for a database table.
 *
 *
 * @author     Fabian Dennler <fd@fabforge.ch>
 * @author     Lukas Bai <bailu@bailu.ch>
 */


class Entity
{

    private $connection = '';
    private $table = '';

    public function __construct($link, $table) {
        $this->connection = $link; // Database connection
        $this->table = $table;
    }

    
    public function load($what, $id) {
        return $this->fetch($what, "WHERE id=?", array($id));
    }


    public function fetch($what, $query = "", $values = array()) {
        $statement = $this->connection->prepare("SELECT " . $what . " FROM " . $this->table . " " . $query);
        $this->bindValues($statement, $values);
        $statement->execute();
        return $statement->fetchAll();
    }



    public function delete($id) {
        $statement = $this->connection->prepare("DELETE FROM " . $this->table . " WHERE id=:id ;");
        $statement->bindParam(':id', $id);
        return $statement->execute();
    }


    public function update($id, $entity) {
        $sql = "UPDATE " . $this->table . " SET " . implode(" = ?, ", array_keys($entity)) . " = ? WHERE id = ? ;";
        $statement = $this->connection->prepare($sql);
        $statement->bindParam(count($entity)+1, $id);
        $this->bindValues($statement, $entity);
        return $statement->execute();
    }


    public function create($entity) {
        $fields = implode(", ", array_keys($entity));
        $values = " " . str_repeat("?, ", count($entity) - 1) . "? ";
        $sql = "INSERT INTO " . $this->table . " ($fields) VALUES ($values);";
        $statement = $this->connection->prepare($sql);
        $this->bindValues($statement, $entity);

        return $statement->execute();
    }


    private function bindValues($statement, $entity) {
        $v = 1;
        foreach($entity as $val) {
            $statement->bindValue($v++, $val);
        }
    }
}
