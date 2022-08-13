package Object;

	public class Traveler {
		protected String ID;
		protected boolean Hidden;
		protected Asteroid CurrentAsteroid;
	
		public Traveler(String id, Asteroid a ){
			this.ID = id;
			this.Hidden = false;
			CurrentAsteroid = a;
		}
		
	
		
		public void Hide() {
			Hidden = true ;
		}
		
		public void Drill() {
			if (this.CurrentAsteroid.getDepth() > 0){
				this.CurrentAsteroid.setDepth(CurrentAsteroid.getDepth() - 1);;
			}
		}
		
		public void SunStorm() {
			if (Hidden==false) {
				this.Die();
			}
		}
		public void Die(){
			System.out.println("your traveler died") ;
		}
	}


