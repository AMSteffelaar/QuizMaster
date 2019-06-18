package model.entity.User;

    public class Teacher extends User {

        private static final String ROLE = "Teacher";

        public Teacher(String name, String password){
            super(name, password);
            super.role = ROLE;
        }

    }
