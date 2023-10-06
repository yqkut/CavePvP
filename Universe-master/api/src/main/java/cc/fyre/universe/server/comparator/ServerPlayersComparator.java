package cc.fyre.universe.server.comparator;

import cc.fyre.universe.server.Server;

import java.util.Comparator;

/**
 * @author xanderume@gmail (JavaProject)
 */
public class ServerPlayersComparator implements Comparator<Server> {

    @Override
    public int compare(Server o1,Server o2) {
        return Integer.compare(o1.getOnlinePlayers().get(),o2.getOnlinePlayers().get());
    }
}
