#!/bin/bash
PASS = `pwgen -s 40 1`

mysql -u root -p <<MYSQL_SCRIPT

create DATABASE Grades

MYSQL_SCRIPT