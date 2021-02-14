#!/bin/bash

curl -i -X POST http://localhost/php/place/php/rest/student/ -H Content-Type: application/json --data-binary @student.json
