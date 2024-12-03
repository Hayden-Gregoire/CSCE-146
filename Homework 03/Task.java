/*
Written By Hayden Gregoire
*/
public class Task {
    private String action;
    private int priority;

    public Task(String action, int priority) {
        this.action = action != null ? action : "none";
        this.priority = priority >= 0 && priority <= 4 ? priority : 4;
    }

    public String getAction() {
        return action;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return priority == task.priority && action.equals(task.action);
    }

    @Override
    public String toString() {
        return "[Task] Priority: " + priority + " Task: " + action;
    }
}