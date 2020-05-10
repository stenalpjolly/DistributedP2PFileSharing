Distributed peer-to-peer file sharing system
---
Goal
The goal is to implement a simple gnutella-like file sharing system with a command line interface. The protocol specification can be found on this web page. The protocol can be simplified, with the basic goal of being able to search for a file on a single node and download it. You’re free to use any programming language for the implementation.
---

#### Commands

help                : lists details of available commands
open <host:port>    : connects to a node on the network
close <id>          : closes connection by connection id (see info command)
info                : prints list of connected hosts with an id for each
find <keyword>      : search files on the network and lists results with an id for each entry
get <id>            : download a file by id

---

**Execution**
Execute the main program with "Absolute Shared path" as argument 

You can configure the TTL value from the config.properties file

PS: I have only test this in Mac and Linux.
