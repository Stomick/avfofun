package evfor.fun.skvader.exceptions;

import java.util.List;

public class PermissionException extends Exception {
    private List<String> permissionsDenied;

    public PermissionException(List<String> permissionsDenied) {
        this.permissionsDenied = permissionsDenied;
    }

    @Override
    public String getMessage() {
        return String.valueOf(permissionsDenied);
    }

    public List<String> getPermissionsDenied() {
        return permissionsDenied;
    }
}
