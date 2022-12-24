# Space-Colonies
Populate available planets with applicants (Person) based on which planet their skillset matches. The Applicants will be added to a queue (first in first out),
from reading a file with a list of applicants and their skillsets. An applicant will have a skillset (medicine, technology, agriculture) and possibly a preferred planet.
To be admitted to a planet, the applicant must have a skillset that is greater than the planet's skillset. If an applicant can not be added to a planet, it will return 
home. The SpaceWindow class shows this process update in real time.
