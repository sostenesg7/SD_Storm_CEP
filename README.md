# Distributed Real time data analysis using Storm and Espertech to identify possible vehicle accident after a transit infringement

This project have a main goal of identify possibles accident caused after a transit infringement. Thi is possible by crossing stream data about infringements and accidents on Recife city.

After a happen transit infringement on some location, the pattern wait for a accident on same location, then show this infringement as possible cause to accident.

We are using the Apache Storm to make a distributed topology and EsperTech to data processing:
twho Spouts(1°:stream accident data; 2°:stream infringement data)
three Bolts(1°:process accident data, 2°: process infringement data, by selecting relevant informations;finaly all collected data is proccessed by a Bolt with EsperTech realtime data analisys library, uging pattern)
