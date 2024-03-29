/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *	 notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *	 notice, this list of conditions and the following disclaimer in the
 *	 documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *	 contributors may be used to endorse or promote products derived
 *	 from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * @Version Spring 2023
 * @Author Oracle
 * @Author Ira Goldstein - Added comments and cleaned up program termination
 */ 

import java.io.*;
import java.net.*;

public class KnockKnockClient {
	public static void main(String[] args) throws IOException {
		
		// get host and port number from the command line
		if (args.length != 2) {
			System.err.println(
				"Usage: java EchoClient <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		try (
			// instantiate the a socket object to the knock knock server
			Socket kkSocket = new Socket(hostName, portNumber);
				
			// to instantiate "out", we use the constructor of PrintWriter class, 
			// this constructor takes one parameter which is an output stream for the socket, 
			PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
			
			// instantiate the "in" object that we will use to read from kkSocket.
			BufferedReader in = new BufferedReader(
				new InputStreamReader(kkSocket.getInputStream()));
		) {
			// create a buffered reader from the standard input
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			// messages from the server and from the user
			String fromServer;
			String fromUser;

			while ((fromServer = in.readLine()) != null) {
				// display messages from the server
				System.out.println("Server: " + fromServer);
				
				// if we receive "Bye." then close the connection and end the program
				if (fromServer.equals("Bye.")) {
					kkSocket.close();
					break;
				}
				
				fromUser = stdIn.readLine();
				if (fromUser != null) {
					// echo back what we typed in
					System.out.println("Client: " + fromUser);
					
					// send what we typed in  to the server
					out.println(fromUser);
				}
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
				hostName);
			System.exit(1);
		}
	}
}
