package mapreduce;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import mapreduce.messages.Result;
import mapreduce.actors.MasterActor;

public class MapReduceApplication {

	public static void main(String[] args) throws Exception {
		
	    ActorSystem _system = ActorSystem.create("MapReduceApp");
		ActorRef master = _system.actorOf(Props.create(MasterActor.class), "master");
		
		System.out.println("master.tell");
			
		master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog", null);
		master.tell("Dog is man's best friend", null);
		master.tell("Dog and Fox belong to the same family", null);
		
		System.out.println("Thread.sleep");
		Thread.sleep(500);
		
		master.tell(new Result(), null);
		
		Thread.sleep(500);
		
		System.out.println("shutdown");
		_system.shutdown();
	}
	
}
