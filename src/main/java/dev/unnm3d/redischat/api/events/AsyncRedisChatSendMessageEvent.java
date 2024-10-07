package dev.unnm3d.redischat.api.events;

import dev.unnm3d.redischat.api.objects.Channel;
import dev.unnm3d.redischat.api.objects.ChatMessage;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class AsyncRedisChatSendMessageEvent extends Event implements Cancellable {
    private boolean cancelled = false;
    private static final HandlerList HANDLERS = new HandlerList();
    @Getter private final ChatMessage chatMessage;
    @Getter private final Channel channel;
    @Getter@Setter private Set<Player> recipients;
    @Getter@Setter private boolean sendToDiscord = true;
    @Getter private final boolean incoming;

    public AsyncRedisChatSendMessageEvent(ChatMessage chatMessage, Channel channel, Set<Player> recipients, boolean incoming) {
        super(true);
        this.chatMessage = chatMessage;
        this.channel = channel;
        this.recipients = recipients;
        this.incoming = incoming;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
