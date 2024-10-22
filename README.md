# Counter App

## Known issue in KSP

Because of [this bug](https://github.com/google/ksp/issues/2072), once in a while I have to run this
command to kill the kotlin
daemon: `jps | grep -E 'KotlinCompileDaemon' | awk '{print $1}' | xargs kill -9 || true`

## Next steps

// TODO: Animations on counter creation and deletion

// TODO: Change Language

// TODO: error treatment

// TODO: securiry features

// TODO: Accessibility: make adaptable UI to different screen sizes
