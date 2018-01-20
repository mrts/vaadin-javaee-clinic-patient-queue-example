#!/bin/bash

case "`uname`" in
	MINGW*) CHROME_CMD='/c/Program Files (x86)/Google/Chrome/Application/chrome.exe';;
	*) CHROME_CMD='google-chrome';;
esac

APP_PREFIX='--app=http://localhost:8080/vaadin-javaee-clinic-patient-queue-example-ui-1.0-SNAPSHOT'

for app in registration-kiosk doctors-office infoscreen
do
	"$CHROME_CMD" "$APP_PREFIX/$app"
done
