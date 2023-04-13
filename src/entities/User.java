    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package entities;

    import java.sql.Date;



    /**
     *
     * @author Azyyzme01
     */
    public class User {
        private int id  ;
        private String name;
        private String prenomc;
         private String num_tel;
        private String city;
        private String email;
        private String password;
        private String roles;
       

        public User( String name, String prenomc,String num_tel, String city, String email, String password,  String roles) {

            
            this.name = name;
            this.prenomc = prenomc;
            this.num_tel = num_tel;
            this.city = city;
            this.email = email;
            this.password = password;
            this.roles = roles;

        }

       public User( int id,String name, String prenomc, String num_tel,String city, String email, String password,  String roles) {

            
            this.name = name;
            this.prenomc = prenomc;
            this.num_tel = num_tel;
            this.city = city;
            this.email = email;
            this.password = password;
            this.roles = roles;

        }



        public User() {

        }

        public User(int i, String string, int i0, int i1, String lastname, String adresse, String photo, String ddd, String sdfdf7, Date date, String string0, String fddf) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    

                    ", name='" + name + '\'' +
                    ", prenomc='" + prenomc + '\'' +
                    ", num_tel=" + num_tel +
                    ", city='" + city + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", roles='" + roles + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNumTel() {
            return num_tel;
        }

        public void setNumTel(String num_tel) {
        this.num_tel = num_tel;
    }





        public String getName() {
            return name;
        }

        public void setName(String name) {
    this.name = name;
}


        public String getPrenomc() {
            return prenomc;
        }

        public void setPrenomc(String prenomc) {
            this.prenomc = prenomc;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }





        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

    }
