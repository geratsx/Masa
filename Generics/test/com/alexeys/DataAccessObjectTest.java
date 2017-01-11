package com.alexeys;

import com.alexeys.users.BasicUserImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class DataAccessObjectTest {

    @Test
    public void testDAO() {
        final IDataAccessObject<BasicUserImpl, MyRuntimeException> dao = new IDataAccessObject<BasicUserImpl, MyRuntimeException>() {
            @Override
            public BasicUserImpl create(final BasicUserImpl basicUser) {
                return null;
            }

            @Override
            public List<MyRuntimeException> batchCreate(final List<BasicUserImpl> batch) {
                return null;
            }

            /**
             * Note that U stays a type, since it's not related to T and E defined in the interface
             * @param basicUser
             * @param u
             * @param <U>
             * @return
             */
            @Override
            public <U extends String> MyRuntimeException combine(final BasicUserImpl basicUser, final U u) {
                return null;
            }

            @Override
            public <L> MyRuntimeException merge(final L l) {
                return null;
            }
        };
    }

    private class MyRuntimeException extends RuntimeException {
    }
}