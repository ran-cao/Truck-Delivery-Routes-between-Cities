# Truck Delivery Routes between Cities
Design an optimal delivery routes for trucks using a revised version of Dijkstra’s algorithm and Graph Theory

### Project Glimpse
This project is an application of directed graph. 
The general idea is that all cities serve the function of vertices and all roads are edges.
Having cities and roads together, we have a directed graph. 
Data structures such as ArrayList and PriorityQueue were used and a revised version of Dijkstra’s algorithm was implemented in this project.
After running the project, the result matched with the given one, and the total travel distances were down roughly by 10%.   

### Problem Setup
Given a map of all cities and roads, a list of warehouses, and a list of cargos needed to be delivered to different warehouses. 
The cargos are allowed to arrive in different trucks, as long as they are all delivered. 
Trucks started from the logistics center, which was one of the cities, to deliver cargos.
The trucks were sent one by one. In other words, there was only one truck performing the deliver task each time.
The truck would finish the trip and return when either the remaining capacity could not support any shipment to adjacent city or there was no more warehouse needed to be delivered cargos. 
Each truck had the 500 capacity which could not be exceeded. The purpose of this project was to schedule the truck routes in order to ship out all the cargos while minimizing the total distance traveled by all trucks. 
After running the program, we were expected to see that our result match up with the given one.

### Our ultimate goal was to minimize the total traveled distance
Two methods to perform our “greedy” approach: 
- To optimize the program, we reconstructed our model by choosing the road with less value of distance per cargo (distance divided by the number of cargos that could be delivered to the warehouse). 
- Extra cost: for the road with distance was greater than C, the value of distance per cargo would be double for weighting long distance road less important. 

For hypothesis, second approach was believed to have a better performance. 
This experiment was to find out the optimal C value and aimed to shorten the total traveled distance by 10%.

### Classes Design
Six classes were designed for this project: Road, City, Warehouse, Cargo, Truck, and ExperimentController. The Road class created road objects which functioned as the real road in life: connecting cities and holding distance between two cities. The City class created city objects which represented cities and some of the city objects contained warehouse objects. The Warehouse class created warehouse objects which held the list of cargos needed to be delivered by truck. The Cargo class created cargo objects with basic information of the cargo. The truck objects created by the Truck class would go through cities’ warehouse to deliver cargos. The ExperimentController class conducted the experiment.

`Road.java` created the road objects, which had the same function with the edges in Graph. Each road object connected two cities object by containing those two city objects and the distance between them. In the normal version, the two road objects could be compared by the distances w stored in them. In this way, the road with shortest distance would be chosen for next deliver. 

`City.java` created the city objects and they were the same as the vertex in Graph. Each city object had its own name and the list of neighbors. In order to work for this project, some city objects contained the warehouse house objects. Two city objects could be compared by alphabetic order of their names. 

`Warehouse.java` created warehouses, which were stored in city objects. Each warehouse object had its own cargo list containing cargos that needed to be deliver to this warehouse. The cargo list was implemented by PriorityQueue because trucks were designed to deliver the lightest cargo first.

`Cargo.java` class created cargo objects. Each cargo object had its own number, weight, and the name of the warehouse which contained it. The cargo objects were stored in the PriorityQueue list of warehouses. The cargo would be taken by the truck from the warehouse for “delivering the cargo to the warehouse”. 

`Truck.java` class worked for creating truck objects. The truck objects recorded the total distance that it traveled and contained all the cargo objects it “delivered to the warehouses”. The truck class provided shipment method which checked whether the truck would ship any cargo to the given next city(warehouse). In addition, if the shipment was available, the truck would go to the next given city, deliver cargos, and print out the shipment information. 

`ExperimentController.java` served for two main tasks: setting up the graph and conducting the experiment. The ExperimentController class provided read and add methods for reading given files and setting up the graph. In addition, the ExperimentController class provided shortest path method through implementing revised version Dijkstra’s algorithm. The revised version Dijkstra’s algorithm would create ArrayList of neighbors with increasing distance order and calculate the distance from the city to the center city. The experiment was conducted by trucks delivering cargos to warehouses. For the normal version ExperimentController, the truck would check the closest road whether the shipment was available to the city connected by this road. The truck would go there if the shipment was available. Otherwise, the truck would check the second closest road. Those processes were recursively called for the truck until either the remaining capacity could not support any shipment to adjacent city or there was no more warehouse needed to be delivered cargos. For the first scenario, the truck would return and the ExperimentController would send a new truck. For the second situation, the experiment finished. For the extra point version, instead of checking the closest city, the truck would check the road with the smallest value of distance per cargo. In addition, for the road with distance was greater than 35, the value of distance per cargo would be double for weighting long distance road less important.
