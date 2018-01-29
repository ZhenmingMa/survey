package com.cby.orange.delegate;


public abstract class OrangeDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends OrangeDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
