#Forntend
## Vorbedingungen
Das frontend sollte über einen Proxy laufen, damit die Requests keine CORS Fehler anzeigen.
Alternativ kann auch das Backend durch Nginx oder der gleichen geproxied werden und dabei
der Access-Control-Allow-Origin header gesetzt werde.

Wird dies nicht getan, funktionieren unter anderem die lösch-Funktionen nicht.