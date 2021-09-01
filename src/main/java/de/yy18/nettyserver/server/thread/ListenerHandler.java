package de.yy18.nettyserver.server.thread;

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

    public boolean remove(@NonNull final Listener threadRunnable) {
        return threadRunnableList.remove(threadRunnable);
    }

    public Iterator<Listener> iterator() {
        return threadRunnableList.iterator();
    }

    public void stopAllHandler() {
        for (Listener listener : this.threadRunnableList) {
            listener.stop();
            this.threadRunnableList.remove(listener);
        }
    }

}
