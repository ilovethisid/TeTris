
public class Clock {
	int currentSecond;
	boolean set;
	long start;
	double measure;
	
	Clock() {
		
	}
	
	void wait(double time) {
		currentSecond=0;
		set=true;
		start=System.currentTimeMillis();
		int waitTime=(int)(time*1000);
		
		while(System.currentTimeMillis()<start+waitTime) {
			// wait
		}
		set=false;
	}
	
	/*void wait(double time,Window.InGameWindow inGameWindow,ReferenceVars refVars) {
		currentSecond=0;
		set=true;
		start=System.currentTimeMillis();
		int waitTime=(int)(time*1000);
		
		while(System.currentTimeMillis()<start+waitTime) {
			if((System.currentTimeMillis()-refVars.startTime)%1000==0) {
				updateTimer(inGameWindow,refVars);
				this.wait(0.001);
			}
			
			// wait
		}
		set=false;
	}*/
}
