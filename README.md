# A Music Library for Database.com

This small sample application is a music library with an album, artist and genre entity.

For more information on how to use the Java SDK, please read our documentation at http://forcedotcom.github.com/java-sdk/force-sdk-overview

# Set the connection URL for Database.com

This application has a persistence unit called `forceDatabase`. The Database.com JPA provider will look for a connection URL in an environment variable called `FORCE_<UPPERCASE_PERSISTENCE_UNIT_NAME>_URL`; in this case, `FORCE_FORCEDATABASE_URL`.

For Linux, set this variable by running the following command after replacing the user and password with your own username and password. Remember to append your security token to the password and to enclose the URL in quotes.

    export FORCE_FORCEDATABASE_URL="force://login.salesforce.com;user=scott@acme.com;password=tigerVXoAIbgYSMOhSEVtcGxgt4mRP"

For Windows, set this variable by running the following command after replacing the user and password with your own username and password.

    set FORCE_FORCEDATABASE_URL=force://login.salesforce.com;user=scott@acme.com;password=tigerVXoAIbgYSMOhSEVtcGxgt4mRP


Please read more documentation at http://forcedotcom.github.com/java-sdk/force-sdk-overview


## Content Attribution

<img alt="Freebase CC-BY" height="23px" style="float: left; border: 0;" width="61px" src="http://www.freebase.com/policies/freebase-cc-by-61x23.png"/>

The sampledata directory contains album, artist and genre data from [Freebase.com](http://www.freebase.com), licensed under [CC-BY](http://creativecommons.org/licenses/by/2.5/). This content includes parts from [Wikipedia](http://en.wikipedia.org/), licensed under the [GFDL](http://www.gnu.org/copyleft/fdl.html).

