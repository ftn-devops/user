package ftn.devops.user.util.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMessagingConstants {

    @UtilityClass
    public class Exchange {

        public static final String USER = "user";
    }

    @UtilityClass
    public class RoutingKey {

        public static final String USER_CREATE = "create";

        public static final String USER_UPDATE = "update";

        public static final String USER_RATED = "rate";
    }
}

