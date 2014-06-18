# static-i18n

## Usage

```
<dependency>
  <groupId>de.bripkens</groupId>
  <artifactId>static-i18n</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Release Process

Have the following variables in your Gradle config file, e.g. `~/.gradle/gradle.properties`.

```
sonatypeUsername=
sonatypePassword=

signing.keyId=
signing.password=
signing.secretKeyRingFile=~/.gnupg/secring.gpg
```

Execute `gradle clean build signArchives uploadArchives` after changing the version manually,
pushing and tagging the release.

## TODO

 - formatter should be configurable
 - interface generator
 - fallback locale

