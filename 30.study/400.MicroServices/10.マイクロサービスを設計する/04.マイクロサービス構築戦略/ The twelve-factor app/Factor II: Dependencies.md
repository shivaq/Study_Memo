## Factor II: Dependencies 
### Explicitly declare and isolate dependencies
* Typically, a cloud application should never rely on any required library or external tool being already present on a system.
* Dependencies should be explicitly declared (for example, using an
npm              file for a Node.js application)
* so that a package manager can pull all these dependencies when deploying a new instance of the application.
* In Go, an application is typically deployed as a statically compiled binary that already contains all required libraries.
* However, even a Go application can be dependent on external system tools (for example, it can fork out to tools such as ImageMagick)
* Ideally, you should deploy tools like these alongside your application.
* This is where container engines, such as Docker, shine.
