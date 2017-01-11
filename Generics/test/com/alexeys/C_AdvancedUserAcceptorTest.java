package com.alexeys;

import com.alexeys.acceptors.AdvancedUserAcceptor;
import com.alexeys.acceptors.IAcceptor;
import com.alexeys.users.AdvancedUserImpl;
import com.alexeys.users.MoreAdvancedUserImpl;
import org.junit.Test;

public class C_AdvancedUserAcceptorTest {

    @Test
    public void testAcceptor() {

        final IAcceptor<AdvancedUserImpl> advancedUserAcceptor = new AdvancedUserAcceptor();

        // This will not work
        // We told that accept() receives AdvancedUser or something more specific
        //advancedUserAcceptor.accept(new BasicUserImpl());

        advancedUserAcceptor.accept(new AdvancedUserImpl());

        advancedUserAcceptor.accept(new MoreAdvancedUserImpl());
    }
}