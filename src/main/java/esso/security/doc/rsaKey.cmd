@rem ACUBE Portal SSO
@rem Generate public/private key pair.
@rem ======================================================

java -classpath .\classes;.\lib\bcprov-jdk14-133.jar com.sds.acube.ep.security.asymmetric.KeyPairGen -default