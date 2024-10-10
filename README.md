Because of [this Bug](https://github.com/google/ksp/issues/2072), I had to run this command to kill
the kotlin daemon: `jps | grep -E 'KotlinCompileDaemon' | awk '{print $1}' | xargs kill -9 || true`
