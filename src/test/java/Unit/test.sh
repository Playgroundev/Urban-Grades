#!/bin/bash
echo 'Setting Database For The First Time, Enter Password For MYSQL'
mysql -u root -p <<MYSQL_SCRIPT

create DATABASE if not exists Grades

MYSQL_SCRIPT