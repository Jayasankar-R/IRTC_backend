# IRTC_backend
LLD:=
Entities-
User
  String name,
  String hashpassword,
  List<TIcket> bookedticket,
  String userId
  
Ticket
  String ticketId,
  String userId,
  String Source,
  String Destination,
  Date dateOfTravel,
  Train

Train
  String trainId,
  String trainNo,
  DateTime departureTime,
  DateTime arraivalTime,
  List<List<boolean>> seats
  Map<Sting,Time>stationTimes
  List<String> stations

Service-
UserBookingService
  Login(User user)
  SigUp(User user)
  fetchTicets()
  Cancel()
  BookTicket()

TrainService
  SearchTrain(String a,String b)
  GetSeatsAvailable(Train train)
  
  
  
  
