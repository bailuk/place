# Happy Place
A simple multi tier sample application. Written in php and javascript.

## Features
- Display list of students.
- Display a map with places where students live.
- Show names of students only when logged in as admin.
- Admin can add, remove and modify records of students.

## Installation and configuration
1. To initialize database load `erm.sql` in directory `db/`:
``` sql
DROP DATABASE place;
source ./erm.sql;
```
2. Copy project to html-root of webserver.
2. To configure database access edit `php/db/connect.php`.
3. Configure access to middle tier by editing `html/js/config.js`.
4. Open address of project root inside webbrowser. This will open the map view.
5. Login:
    - user: hans pw: 123
    - admin: admin pw: admin

## Directory structure
- [db/](db/): scripts and files to initialize database
- [php/](php/): backend: php rest interface
- [html/](html/) (and [index.html](index.html)): html / javascript frontend
