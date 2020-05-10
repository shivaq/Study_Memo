## Definition of trace ID
* マイクロサービスの全てのログのうち、こいつはどこのログだ？をわかりやすくするために
* for every external request coming into your microservices
* you generate a unique ID, and that ID is passed to any internal microservices calls
* used to handle that request. Thus through a search for a single trace ID, you can find all microservices calls resulting from a single external access.
