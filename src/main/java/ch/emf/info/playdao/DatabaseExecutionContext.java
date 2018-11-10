package ch.emf.info.playdao;

import akka.actor.ActorSystem;
import javax.inject.Inject;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutor;

/**
 * Contexte d'exécution personnalisé lié au thread-pool "database.dispatcher".<br>
 * Voir l'exemple original sous https://github.com/playframework/play-java-jpa-example.
 *
 * @author Jean-Claude Stritt
 */
public class DatabaseExecutionContext implements ExecutionContextExecutor {
  private final ExecutionContext dbCtx;
  private static final String NAME = "database.dispatcher";

  @Inject
  public DatabaseExecutionContext(ActorSystem actorSystem) {
    this.dbCtx = actorSystem.dispatchers().lookup(NAME);
  }

  @Override
  public void execute(Runnable command) {
    dbCtx.execute(command);
  }

  @Override
  public void reportFailure(Throwable cause) {
    dbCtx.reportFailure(cause);
  }
}
