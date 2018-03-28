package gregtech.api.recipes.machines;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder.DefaultRecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

public class RecipeMapFurnace extends RecipeMap<DefaultRecipeBuilder> {

    public RecipeMapFurnace(String unlocalizedName, int minInputs, int maxInputs, int minOutputs, int maxOutputs, int minFluidInputs, int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs, int amperage, DefaultRecipeBuilder defaultRecipe) {
        super(unlocalizedName, minInputs, maxInputs, minOutputs, maxOutputs, minFluidInputs, maxFluidInputs, minFluidOutputs, maxFluidOutputs, amperage, defaultRecipe);
    }

    @Override
    @Nullable
    public Recipe findRecipe(long voltage, NonNullList<ItemStack> inputs, List<FluidStack> fluidInputs) {
        Recipe normalRecipe = super.findRecipe(voltage, inputs, fluidInputs);
        if (inputs == null || inputs.size() <= 0 || inputs.get(0).isEmpty())
            return normalRecipe;
        ItemStack output = ModHandler.getSmeltingOutput(inputs.get(0));
        return output.isEmpty() ? normalRecipe : this.recipeBuilder()
            .notOptimized()
            .inputs(GTUtility.copyAmount(1, inputs.get(0)))
            .outputs(output)
            .duration(128).EUt(4)
            .build().getResult();
    }
}
