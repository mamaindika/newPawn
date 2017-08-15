/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 *
 * @author it177479
 */
public class SimpleLdapAuthentication {
    
    public static String authenticateLdap(String username, String password){
        String result = "failed";
        
        //String username = "user";
        //String password = "password";
        //String base = "ou=People,dc=objects,dc=com,dc=au";
        //String base = "dc=BANKOFCEYLON,dc=LOCAL";
        //String dn = "uid=" + "it177479" + "," + base;
        String dn = username.trim() + "@bankofceylon";
        String ldapURL = "ldap://bankofceylon.local:389";
         
        // Setup environment for authenticating
		
		Hashtable<String, String> environment = 
			new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, ldapURL);
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, dn);
		environment.put(Context.SECURITY_CREDENTIALS, password.trim());

		try
		{
			DirContext authContext = 
				new InitialDirContext(environment);
			
			// user is authenticated
                        System.out.println("connected");
                        result = "ok";
                        //System.out.println(authContext.getEnvironment());
                        authContext.close();
			
		}catch (AuthenticationNotSupportedException ex) 
                {
                        System.out.println("The authentication is not supported by the server"+ex);
                        result = "failed";
                }catch (AuthenticationException ex)
		{
                    System.out.println("login failed--"+ex);        
                    result = "failed";
			// Authentication failed

		}
		catch (NamingException ex)
		{
                    System.out.println("login failed--"+ex); 
                    ex.printStackTrace();
                    result = "failed";
		}
        
        return result;
    }
    
}
