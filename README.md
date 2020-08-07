# Truck Delivery Routes between Cities
Design an optimal delivery routes for trucks using a revised version of Dijkstra’s algorithm and Graph Theory

### Problem Setup: 
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

### Conclusion
This project is an application of directed graph. 
The general idea is that all cities serve the function of vertices and all roads are edges.
Having cities and roads together, we have a directed graph. 
Data structures such as ArrayList and PriorityQueue were used and a revised version of Dijkstra’s algorithm was implemented in this project.
After running the project, the result matched with the given one, and the total travel distances were down roughly by 10%.   
