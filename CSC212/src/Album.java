
public class Album {

        private String name;
        private String condition;
        private PhotoManager manager;
        private int NbComps;

        // Constructor
        public Album(String name, String condition, PhotoManager manager)
        {
            this.name = name;
            this.condition = condition;
            this.manager = manager;
            NbComps =0 ;
        }

        // Return the name of the album
        public String getName()
        {
            return name;
        }
        
        // Return the condition associated with the album
        public String getCondition()
        {
            return condition;
        }

        // Return the manager
        public PhotoManager getManager()
        {
            return manager;
        }
        
        // Return all photos that satisfy the album condition
        public LinkedList<Photo> getPhotos()
        {
                LinkedList<Photo> Myphotos = new LinkedList<Photo>();
                {
                    LinkedList<Photo> photos1 = manager.getPhotos();
                    if (! photos1.empty())
                    {
                        photos1.findFirst();
                        while (! photos1.last())
                        {
                        	Myphotos.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
                            photos1.findNext();
                        }
                        Myphotos.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
                    }
                }
                NbComps =0 ;
                
                if (this.condition.compareTo("") != 0)
                {
                    String [] Array = condition.split(" AND ");
                    
                    Myphotos.findFirst();
                    while ( !  Myphotos.last())
                    {
                        Photo photo = Myphotos.retrieve();
                        System.out.println("test " + photo.getPath());
                        if ( ! allAvilable (photo.allTags , Array ))
                        	 Myphotos.remove();
                        else
                        	 Myphotos.findNext();
                    }
                    Photo photo11 =  Myphotos.retrieve();
                    System.out.println("testlast " + photo11.getPath());
                    if ( ! allAvilable (photo11.allTags , Array ))
                    	 Myphotos.remove();
                    else
                    	 Myphotos.findNext();
                }
                return  Myphotos;
        }
       
        // Return the number of tag comparisons used to find all photos of the album
        public int getNbComps()
        {
            return NbComps;
        }

        private boolean allAvilable ( LinkedList<String> AllTags , String [] Array )
        {
            boolean continue1 = true;
            if (AllTags.empty())
                continue1 = false;
            else
            {
                for ( int i = 0 ; i < Array.length && continue1 ; i++)
                {
                    boolean found_in_tags = false;

                    AllTags.findFirst();

                    while (!AllTags.last())
                    {
                        this.NbComps ++ ;    
                        System.out.println(AllTags.retrieve() + " " + Array[i]);
                        if (AllTags.retrieve().compareToIgnoreCase(Array[i]) == 0)
                        {
                            found_in_tags = true;
                            break;
                        }
                        AllTags.findNext();
                    }
                    if (! found_in_tags )
                    {
                        this.NbComps ++ ;
                        System.out.println(AllTags.retrieve() + " " + Array[i]);
                        if (AllTags.retrieve().compareToIgnoreCase(Array[i]) == 0)
                            found_in_tags = true;
                    }
                    if ( ! found_in_tags )
                        continue1 = false;
                }
            }
            return continue1;
        }
       
}

