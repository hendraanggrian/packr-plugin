Packr Plugin
============
[![bintray](https://img.shields.io/badge/bintray-packr-brightgreen.svg)](https://bintray.com/hendraanggrian/packr)
[![download](https://api.bintray.com/packages/hendraanggrian/packr/packr-gradle-plugin/images/download.svg)](https://bintray.com/hendraanggrian/packr/packr-gradle-plugin/_latestVersion)
[![build](https://travis-ci.com/hendraanggrian/packr-gradle-plugin.svg)](https://travis-ci.com/hendraanggrian/packr-gradle-plugin)
[![license](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Gradle plugin of [packr], a library that wraps JARs into native bundle for Windows, MacOS, and Linux.

Download
--------
Add plugin to buildscript:

```gradle
buildscript {
    repositories {
        jcenter()
        maven { url = 'https://oss.sonatype.org/content/repositories/snapshots' }
    }
    dependencies {
        classpath "com.hendraanggrian.packr:packr-gradle-plugin:$version"
    }
}
```

then apply it in your module:

```gradle
apply plugin: 'com.hendraanggrian.packr'
```

Usage
-----
Configure `packr` task, below are available configurations.
See [PackrTask] for meaning of each parameter.

```gradle
task.withType(PackTask) {
    executable 'example'                // default is project's name
    classpath('my.jar', 'other.jar')    // default is empty
    mainClass 'com.example.App'         // must be defined or will throw an exception
    vmArgs('Xmx1G')                     // default is empty
    resources('resources', 'image.jpg') // default is empty
    minimizeJre 'hard'                  // default is `soft`
    outputName 'Example App'            // default is project's name
    outputDir 'packr-output'            // default is `release` directory in build directoy
    
    wrapApp false                       // default is true
    iconDir 'icon.icns'                 // optional, no default
    bundleId 'com.example.app'          // optional, no default
    
    verbose                             // default is false
    openOnDone true                     // default is false
}
```

You can then pack native distribution by providing platform and jdk property:

```gradle
task.withType(PackTask) {
    ...
    mac {
        jdk = 'path/to/jdk' // default is java home
        icon = 'path/to/icon' // only supported in mac
    }
    windows64 {
        jdk = 'path/to/win/jdk'
    }
}
```

Available platforms are `mac`, `windows32`, `windows64`, `linux32`, and `linux64`.

License
-------
    Copyright 2018 Hendra Anggrian

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
[packr]: https://github.com/libgdx/packr
[PackrTask]: https://hendraanggrian.github.io/packr-plugin/packr/com.hendraanggrian.packr/-packr-task/index.html
