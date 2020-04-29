/*
 * Copyright (c) 2019-2020 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.connector.network.translators.block.entity;

import com.github.steveice10.mc.protocol.data.game.world.block.BlockState;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.nukkitx.nbt.CompoundTagBuilder;
import com.nukkitx.nbt.tag.ByteTag;
import com.nukkitx.nbt.tag.Tag;
import org.geysermc.connector.network.translators.block.BlockStateValues;

import java.util.ArrayList;
import java.util.List;

@BlockEntity(name = "ShulkerBox", delay = false, regex = "shulker_box")
public class ShulkerBoxBlockEntityTranslator extends BlockEntityTranslator {

    @Override
    public List<Tag<?>> translateTag(CompoundTag tag, BlockState blockState) {
        List<Tag<?>> tags = new ArrayList<>();

        byte direction = BlockStateValues.getShulkerBoxDirection(blockState);
        // Just in case...
        if (direction == -1) direction = 1;
        tags.add(new ByteTag("facing", direction));

        return tags;
    }

    @Override
    public CompoundTag getDefaultJavaTag(String javaId, int x, int y, int z) {
        return null;
    }

    @Override
    public com.nukkitx.nbt.tag.CompoundTag getDefaultBedrockTag(String bedrockId, int x, int y, int z) {
        CompoundTagBuilder tagBuilder = getConstantBedrockTag(bedrockId, x, y, z).toBuilder();
        tagBuilder.byteTag("facing", (byte)1);
        return tagBuilder.buildRootTag();
    }
}