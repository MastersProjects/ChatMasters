package ch.masters.chatmasters.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ChatClientTest.class, ChatServerTest.class })
public class ChatMastersTestSuite {

}
