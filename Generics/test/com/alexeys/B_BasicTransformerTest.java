package com.alexeys;

import com.alexeys.transformers.BasicTransformer;
import com.alexeys.transformers.ITransformer;
import com.alexeys.users.AdvancedUserImpl;
import com.alexeys.users.BasicUserImpl;
import com.alexeys.users.MoreAdvancedUserImpl;
import org.junit.Test;

/**
 * Created by alexey on 03/02/16.
 */
public class B_BasicTransformerTest {


    @Test
    public void testTransform() {
        final ITransformer<AdvancedUserImpl, BasicUserImpl> advancedTransformer = new BasicTransformer<>();

        final ITransformer<MoreAdvancedUserImpl, AdvancedUserImpl> moreAdvancedTransformer =
                new BasicTransformer<>();

        // This will not work, since AdvancedUser doesn't extend MoreAdvancedUser
        //ITransformer<AdvancedUserImpl, MoreAdvancedUserImpl> wrongTransformer = new BasicTransformer<>();

        final AdvancedUserImpl advancedUser = moreAdvancedTransformer.transform(new MoreAdvancedUserImpl());

        System.out.println(advancedUser);

        final BasicUserImpl moreAdvancedUser = advancedTransformer.transform(advancedUser);

        System.out.println(moreAdvancedUser);
    }

}