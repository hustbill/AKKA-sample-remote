package mapreduce.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import mapreduce.actors.AggregateActor;
import mapreduce.actors.MapActor;
import mapreduce.actors.ReduceActor;
import mapreduce.messages.Result;

public class MasterActor extends UntypedActor {
	
		

	private ActorRef aggregateActor = getContext().actorOf( 
			Props.create(AggregateActor.class), "aggregate");

	private ActorRef reduceActor = getContext().actorOf(
	Props.create(AggregateActor.class), "reduce");



	private ActorRef mapActor = getContext().actorOf(
			Props.create(AggregateActor.class), "map");
	
//	private ActorRef reduceActor = getContext().actorOf(
//			new Props(new UntypedActorFactory() {
//				public UntypedActor create() {
//					return new ReduceActor(aggregateActor);
//				}
//			}), "reduce");
//
//	private ActorRef mapActor = getContext().actorOf(
//			new Props(new UntypedActorFactory() {
//				public UntypedActor create() {
//					return new MapActor(reduceActor);
//				}
//			}), "map");
//	

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			mapActor.tell(message, getSelf());
		} else if (message instanceof Result) {
			aggregateActor.tell(message, getSelf());
		} else
			unhandled(message);
	}
}
