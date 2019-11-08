clear
        mvn package
	ln -sf target/tic-tac-toe-0.1.jar .
	java -jar target/tic-tac-toe-0.1.jar &