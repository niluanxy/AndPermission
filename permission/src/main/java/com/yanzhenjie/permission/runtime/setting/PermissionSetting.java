/*
 * Copyright © Zhenjie Yan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.permission.runtime.setting;

import com.yanzhenjie.permission.bridge.BridgeRequest;
import com.yanzhenjie.permission.bridge.RequestManager;
import com.yanzhenjie.permission.source.Source;

/**
 * <p>Setting executor.</p>
 * Created by Zhenjie Yan on 2016/12/28.
 */
public class PermissionSetting implements Setting, BridgeRequest.Callback {

    private Source mSource;
    private Setting.Action mComeback;

    public PermissionSetting(Source source) {
        this.mSource = source;
    }

    @Override
    public Setting onComeback(Setting.Action comeback) {
        this.mComeback = comeback;
        return this;
    }

    @Override
    public void start() {
        BridgeRequest request = new BridgeRequest(mSource);
        request.setType(BridgeRequest.TYPE_PERMISSION_SETTING);
        request.setCallback(this);
        RequestManager.get().add(request);
    }

    @Override
    public void onCallback() {
        if (mComeback != null) {
            mComeback.onAction();
        }
    }
}