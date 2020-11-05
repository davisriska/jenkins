import com.cloudbees.hudson.plugins.folder.Folder;

/**
 * Returns a list of job full names inside the found folder and its subfolders
 * 
 * def jobList = jobListInsideFolder('Project/NextFolder') 
 * jobList = [
 *   'Project/NextFolder/Job 1', 
 *   'Project/NextFolder/Job 2', 
 *   'Project/NextFolder/SubFolder/Job 3'
 * ]
 **/
def jobListInsideFolder(folderName){
    // Gets parent folder of current running job
    def parentFolder = getContext(Job.class).parent
    
    // Finds top folder of current running job
    while(!(parentFolder.parent instanceof Hudson)){
        parentFolder = parentFolder.parent
    }
    
    // Looks for specified folder inside top folder
    def folder = parentFolder.getAllItems(Folder.class).find { it.fullName.contains(folderName) };
    
    // Returns all jobs inside found folder and subfolders
    return folder?.getAllItems(Job.class).collect { it.fullName } 
}
