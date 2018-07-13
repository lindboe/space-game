## What is this

A turn-based tactical game... in space!

## How do I work on it

### Development

To run a development server with file watching, run `boot run`.

You can get a repl for Clojure files (.clj, .cljc) with `boot repl`. Run it from
the project root.

To get a Clojurescript repl, run `boot cljsrepl`. In another tab, run `boot
repl -c` to start a client-only repl. In that repl, run `(start-repl)`. Load
the application in a browser (localhost:3000), and in the repl client you
should see a "connected!" message after a moment, and now you should be able to
require cljs files and send javascript to the browser. (Note: incognito windows
do not seem to work at the moment.)
