package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entites.Train;
import entites.User;
import util.UserserviceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class UserService {

    private User user;

    private final List<User> userList;

    private final ObjectMapper objectMapper=new ObjectMapper();

    private static final String USER_PATH="app/src/main/java/localDb/user.json";






    public UserService(User user1) throws IOException {
        this.user=user1;
        File users= new File(USER_PATH);
        userList=objectMapper.readValue(users, new TypeReference<List<User>>() {});


    }
    public UserService() throws IOException {
       this.userList= loadUser();
    }
    public List<User> loadUser() throws  IOException{
        File users= new File(USER_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {
        });

    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserserviceUtil.checkPassword(user.getPassword(),user1.getHashpassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public  void signUp(User user1){
        try {
            userList.add(user1);
            saveUserListToFile();
        }catch (IOException ignored){
        }
    }

    public void saveUserListToFile() throws IOException {
        File usersFile = new File(USER_PATH);
        objectMapper.writeValue(usersFile,userList);

    }

    public void fetchBookings(){
        Optional<User> fetchBookings= userList.stream().filter(user1 -> {
            System.out.println(user1.getHashpassword());
            return user1.getName().equals(user.getName()) && UserserviceUtil.checkPassword(user.getPassword(),user1.getHashpassword());
        }).findFirst();
        fetchBookings.ifPresent(User::printTickets);
    }

    public Boolean cancelBooking(String ticketId){



        return  Boolean.TRUE;
    }

    public List<Train> getTrains(String source,String destination){
        try{
            TrainService trainService=new TrainService();
            return trainService.searchTrains(source,destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train,int row,int seat){
        try{
            TrainService trainService= new TrainService();
            List<List<Integer>> seats= train.getSeats();
            if(row >=0 && row< seats.size() && seat >=0 && seat<seats.get(row).size()){
                if (seats.get(row).get(seat)==0){
                    seats.get(row).set(seat,1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true;
                }else {
                    return false;//seat already booked

                }
            }else return false;//invalid row
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }


}
