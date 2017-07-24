@rem ACUBE Portal SSO
@rem Generate public/private key pair.
@rem ======================================================

@rem java -classpath .\classes com.sds.acube.ep.security.seed.MakeRoundKey /my/path/seed.key
java -classpath .\classes com.sds.acube.ep.security.seed.MakeRoundKey
