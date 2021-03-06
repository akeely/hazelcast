/*
 * Copyright (c) 2008-2015, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.collection.impl.queue.operations;

import com.hazelcast.monitor.impl.LocalQueueStatsImpl;
import com.hazelcast.collection.impl.queue.QueueDataSerializerHook;

/**
 * check if queue is empty
 */
public class IsEmptyOperation extends QueueOperation {

    public IsEmptyOperation() {
    }

    public IsEmptyOperation(final String name) {
        super(name);
    }

    @Override
    public void run() throws Exception {
        response = getOrCreateContainer().size() == 0;
    }

    @Override
    public void afterRun() throws Exception {
        LocalQueueStatsImpl stats = getQueueService().getLocalQueueStatsImpl(name);
        stats.incrementOtherOperations();
    }

    @Override
    public int getId() {
        return QueueDataSerializerHook.IS_EMPTY;
    }
}
