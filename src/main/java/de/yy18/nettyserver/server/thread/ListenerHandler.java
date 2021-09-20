package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.util.ConsoleWriter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
public final class ListenerHandler {

    private static final ListenerHandler INSTANCE = new ListenerHandler();

    public static ListenerHandler getINSTANCE() {
        return INSTANCE;
    }

    private final List<Listener> threadRunnableList = new ArrayList<>();

    public void add(@NonNull final Listener threadRunnable) {
        threadRunnableList.add(threadRunnable);
    }

    public void remove(@NonNull final Listener threadRunnable) {
        threadRunnableList.remove(threadRunnable);
    }

    public Iterator<Listener> iterator() {
        return threadRunnableList.iterator();
    }

    public synchronized void stopAllHandler() {
        if(this.threadRunnableList.isEmpty()) return;
        for (Listener listener : threadRunnableList) {
            listener.stop();
        }
        this.threadRunnableList.clear();
    }

}
